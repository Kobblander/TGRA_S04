package com.tgra.client.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 10/18/2014
 * Time : 01:17
 */
public class XMLReader {
    /**
     * Read the xml config file
     * @param path Xml file path
     * @param child children to be returned
     */
    public static Array<XmlReader.Element> read(String path, String child) {
        FileHandle file = Gdx.files.internal(path);
        XmlReader reader = new XmlReader();
        XmlReader.Element root = reader.parse(file.readString());

        return root.getChildrenByName(child);
    }
}
