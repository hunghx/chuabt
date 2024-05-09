package ra.demo.jspservlet.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String author;
    private Category category;
}
