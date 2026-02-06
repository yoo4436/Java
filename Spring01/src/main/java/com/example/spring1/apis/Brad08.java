package com.example.spring1.apis;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brad08")
public class Brad08 {
    @Autowired
    private  NamedParameterJdbcTemplate jdbc;
    
    @RequestMapping("/test1")
    public void test() {
        String sql = """
                insert into cust
                    (cname, tel, birth)
                values
                    (:cname, :tel, :birth)
                """;
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("cname", "111");
        map.put("tel", "01-0562");
        map.put("birth", "1999-05-07");

        int n = jdbc.update(sql, map);
        System.out.println(n);
    }
}
