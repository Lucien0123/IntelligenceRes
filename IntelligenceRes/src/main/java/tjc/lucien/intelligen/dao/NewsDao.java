package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.NewsAnnouncement;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
@Repository
public interface NewsDao extends JpaRepository<NewsAnnouncement, Integer> {

    Page<NewsAnnouncement> findByPublishedAndCompanyName(boolean published, String comName, Pageable publicDate);

    List<NewsAnnouncement> findByPublisherAndNewsTitle(String realname, String newsTitle);

    NewsAnnouncement findById(int id);

    NewsAnnouncement findByTaskId(int taskId);

    Page<NewsAnnouncement> findByPublished(boolean b, Pageable publicDate);

    /* 获取所有发布的新闻 */
    List<NewsAnnouncement> findByPublished(boolean b);
}
