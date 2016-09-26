package com.sample.test;

import com.sample.core.model.dto.Req;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.*;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by andongxu on 9/23/16.
 */
public class SerializeTest {

    public static void main(String [] args) throws Exception {
        SerializeTest st = new SerializeTest();
        st.tmp();

    }

    public void tmp() throws Exception {
        Bean b1 = new Bean(1001, "hello");

        byte [] ob1 = SerializeUtil.serializeObject(b1);

        Bean b2 = SerializeUtil.deserializeObject(ob1, Bean.class);

        Assert.assertNotNull(b2);

    }

}
