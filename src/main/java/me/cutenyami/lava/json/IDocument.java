package me.cutenyami.lava.json;

import java.util.List;
import java.util.Map;

public interface IDocument<D extends IDocument<?>> {
    D append(String paramString, Object paramObject);

    D depend(String paramString);

    <T> T get(String paramString, Class<T> paramClass);

    <T> List<T> getList(String paramString, Class<T> paramClass);

    <T1, T2> Map<T1, T2> getMap(String paramString, Class<T1> paramClass, Class<T2> paramClass1);
}
