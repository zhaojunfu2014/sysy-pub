package spring.junit;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.zjf.cms.dao.ArticleDao;
import com.zjf.cms.dao.MenuDao;
import com.zjf.cms.entity.Article;
import com.zjf.cms.entity.Menu;
@ContextConfiguration("file:WebRoot/WEB-INF/applicationContext.xml")
public class TestDao extends AbstractJUnit4SpringContextTests {
	@Resource
	private MenuDao menuDao;
	@Resource
	private ArticleDao articleDao;
	@Test
	public void test1() throws Exception {
		Article a = new Article();
		a.setTitle("��һƪ����");
		a.setContent("���̾��Ͽ��˼��ֿ��ֵ�ʱ�䷴�������˿�ݷ�ʽ�ɶ��˷�������˵��ҷֿ��˵�����");
		a.setPubDate(new Date());
		articleDao.save(a);
//		menuDao.save(new Menu());
	}
	@Test
	public void test2() throws Exception {
		Article a= articleDao.fetch(Article.class, new Long(1));
		Menu m = menuDao.fetch(Menu.class, 4);
		a.setMenu(m);
		articleDao.update(a);
	}
	@Test
	public void test3() throws Exception {
		Menu m = menuDao.fetch(Menu.class, 4);
		System.out.println(m.getArticles().size());
	}
	
}
