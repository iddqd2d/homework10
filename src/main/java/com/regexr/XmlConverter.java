package com.regexr;


import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;

public class XmlConverter {
    private static final Logger LOG = Logger.getLogger(XmlConverter.class);

    public void xmlObjectInit() {
        List<Map<List<Set<Integer>>, String>> list = (ArrayList) add(new ArrayList(),
                add(new HashMap(),
                        (add(new ArrayList(),
                                add(new HashSet(), Integer.BYTES)))
                        , XmlConverter.class.getName()
                ));
        objToXmlFile(list);
    }

    private Collection add(Collection obj, Object arg1) {
        obj.add(arg1);
        return obj;
    }

    private Map add(Map obj, Collection arg1, Object arg2) {
        obj.put(arg1, arg2);
        return obj;
    }


    public void objToXmlFile(List list) {
        try (XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream("obj.myxml"))) {
            xmlEncoder.writeObject(list);
        } catch (IOException e) {
            LOG.info(e.getMessage());
        }
    }
}
