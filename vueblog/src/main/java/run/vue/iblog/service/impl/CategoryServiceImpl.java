package run.vue.iblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import run.vue.iblog.entity.Blog;
import run.vue.iblog.entity.Category;
import run.vue.iblog.mapper.BlogMapper;
import run.vue.iblog.mapper.CategoryMapper;
import run.vue.iblog.service.BlogService;
import run.vue.iblog.service.CategoryService;

/**
 * @author yongxin
 * @Date 2021/5/21
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
