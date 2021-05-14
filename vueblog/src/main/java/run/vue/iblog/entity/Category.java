package run.vue.iblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yongxin
 * @Date 2021/5/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_category")
public class Category implements Serializable {
    @TableId(value="cid", type = IdType.AUTO)
    private Long cid;

    @NotBlank(message = "标题不能为空")
    private String category;

}
