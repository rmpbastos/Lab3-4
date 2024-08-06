package javat;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Component
@Service
public class Catdao {

    JdbcTemplate template;

    public Catdao(JdbcTemplate template) {
        this.template = template;
    }

    public void setTemplate(JdbcTemplate template) {

        this.template = template;

    }

    public List<Category> display() throws ClassNotFoundException, SQLException {
        return template.query("SELECT * FROM category", (rs, row) -> {

            Category c = new Category();
            c.setCatcode(rs.getString(1));
            c.setCatdesc(rs.getString(2));
            return c;
        });
    }


}
