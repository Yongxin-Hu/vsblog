package run.vue.iblog.service.impl;

import run.vue.iblog.entity.Blog;
import run.vue.iblog.mapper.BlogMapper;
import run.vue.iblog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yongxin
 * @since 2021-02-14
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
