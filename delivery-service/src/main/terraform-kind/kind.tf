terraform {
  required_providers {
    kind = {
      source = "justenwalker/kind"
      version = "0.11.0-rc.1"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = ">= 2.0.0"
    }
  }
}

provider "kind" {
  # Configuration options
  kubeconfig = pathexpand("~/.kube/config")
}

resource "kind_cluster" "new" {
  name = "test"
  config = <<-EOF
        apiVersion: kind.x-k8s.io/v1alpha4
        kind: Cluster
        nodes:
        - role: control-plane
        - role: worker
        - role: worker
    EOF
}
provider "kubernetes" {
  config_path = "~/.kube/config"
}
resource "kubernetes_namespace" "formation" {
  metadata {
    name = "formation"
  }
}
resource "kubernetes_deployment" "formation" {
  metadata {
    name      = "delivery-service"
    namespace = kubernetes_namespace.formation.metadata.0.name
  }
  spec {
    replicas = 2
    selector {
      match_labels = {
        app = "delivery-service-app"
      }
    }
    template {
      metadata {
        labels = {
          app = "delivery-service-app"
        }
      }
      spec {
        container {
          image = "dthibau/delivery-service:0.0.3"
          name  = "delivery-service-container"
          port {
            container_port = 8080
          }
        }
      }
    }
  }
}
resource "kubernetes_service" "formation" {
  metadata {
    name      = "delivery-service"
    namespace = kubernetes_namespace.formation.metadata.0.name
  }
  spec {
    selector = {
      app = kubernetes_deployment.formation.spec.0.template.0.metadata.0.labels.app
    }
    type = "NodePort"
    port {
      node_port   = 30201
      port        = 80
      target_port = 8080
    }
  }
}