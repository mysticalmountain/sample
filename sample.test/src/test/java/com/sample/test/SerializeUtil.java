package com.sample.test;

import com.sample.core.model.dto.Req;

import java.io.*;

/**
 * Created by andongxu on 9/23/16.
 */
public final class SerializeUtil {

    public static byte[] serializeObject(Object object) throws IOException {
        ByteArrayOutputStream saos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(saos);
        oos.writeObject(object);
        oos.flush();
        return saos.toByteArray();
    }

    public static <C> C deserializeObject(byte[]buf, Class<C> c) throws IOException, ClassNotFoundException {
        ByteArrayInputStream sais=new ByteArrayInputStream(buf);
        ObjectInputStream ois = new ObjectInputStream(sais);
        Object obj = ois.readObject();
        return  (C) obj;
    }


}
