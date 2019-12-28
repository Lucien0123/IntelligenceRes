package tjc.lucien.intelligen.utils;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
public class SortCollectionUtil {

    public static List<JSONObject> sortJsonListBy(List<JSONObject> jsonList, String key) {

        int len = jsonList.size();
        JSONObject a = new JSONObject();
        JSONObject b = new JSONObject();
        JSONObject temp = new JSONObject();
        int x = -1;
        int y = -1;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                a = jsonList.get(i);
                b = jsonList.get(j);
                x = Integer.parseInt(a.getString(key));
                y = Integer.parseInt(b.getString(key));
                if (x > y){
                    temp = a;
                    a = b;
                    b = temp;
                    jsonList.set(i, a);
                    jsonList.set(j, b);
                }
            }
        }

        return jsonList;
    }
}
