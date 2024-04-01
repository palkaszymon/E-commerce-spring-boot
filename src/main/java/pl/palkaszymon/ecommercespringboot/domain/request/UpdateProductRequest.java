package pl.palkaszymon.ecommercespringboot.domain.request;

public record UpdateProductRequest(String name, String description, String category, Double price) {
}
