package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LRUCache extends LinkedHashMap {

    private int max;

    public LRUCache(int max){
        super(16, 0.75f, true);
        this.max = max;
    }

    protected boolean removeEldestEntry(Entry eldest) {
        return super.size() > max;
    }

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("1", "a");
        map.put("3", "c");
        map.put("2", "b");
        map.put("4", "d");

        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        Map<String, String> map2 = new HashMap<>();
        map.put("1", "a");
        map.put("3", "c");
        map.put("2", "b");
        map.put("4", "d");

        Iterator<Entry<String, String>> iterator2 = map2.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("以下是accessOrder=true的情况:");

        map = new LinkedHashMap<String, String>(10, 0.75f, true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.get("2");//2移动到了内部的链表末尾
        map.get("4");//4调整至末尾
        map.put("3", "e");//3调整至末尾
        map.put(null, null);//插入两个新的节点 null
        map.put("5", null);//5
        iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
