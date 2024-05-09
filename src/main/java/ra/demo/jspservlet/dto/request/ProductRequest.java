package ra.demo.jspservlet.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    private String name;
    private Double price;
    private Integer quantity;
    private String author;
    private Integer categoryId;
}
