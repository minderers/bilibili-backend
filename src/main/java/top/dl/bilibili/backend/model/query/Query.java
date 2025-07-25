package top.dl.bilibili.backend.model.query;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class Query {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为1")
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Range(min = 1, max = 100, message = "每页条数，取值范围1-100")
    private Integer limit;

    private String order;
    private boolean asc;
}