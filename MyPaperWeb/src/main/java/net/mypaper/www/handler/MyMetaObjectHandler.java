package net.mypaper.www.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp time = Timestamp.from(Instant.now());
        this.setFieldValByName("date", time,metaObject);
        this.setFieldValByName("comment_time",time,metaObject);
        this.setFieldValByName("upload_date",time,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
