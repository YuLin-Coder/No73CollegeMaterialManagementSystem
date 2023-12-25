package cn.huanzi.qch.baseadmin.goods.service;

import cn.huanzi.qch.baseadmin.mbg.mapper.InStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.mapper.OutStoreMapper;
import cn.huanzi.qch.baseadmin.mbg.model.InStoreExample;
import cn.huanzi.qch.baseadmin.mbg.model.OutStore;
import cn.huanzi.qch.baseadmin.mbg.model.OutStoreExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticsService {

    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private OutStoreMapper outStoreMapper;

    public Map<String, Object> in() {
        Map<String, Object> result = new HashMap<>();

        List<String> type = Arrays.asList("固定资产", "消耗物品", "福利物品");

        List<Integer> count = new ArrayList<>();

        InStoreExample inStoreExample1 = new InStoreExample();
        inStoreExample1.createCriteria().andTypeEqualTo("固定资产");
        count.add(inStoreMapper.countByExample(inStoreExample1));

        InStoreExample inStoreExample2 = new InStoreExample();
        inStoreExample2.createCriteria().andTypeEqualTo("消耗物品");
        count.add(inStoreMapper.countByExample(inStoreExample2));

        InStoreExample inStoreExample3 = new InStoreExample();
        inStoreExample3.createCriteria().andTypeEqualTo("福利物品");
        count.add(inStoreMapper.countByExample(inStoreExample3));

        result.put("mapValue", count.toArray());
        result.put("mapName", type.toArray());
        return result;
    }

    public Map<String, Object> out() {
        Map<String, Object> result = new HashMap<>();

        List<String> type = Arrays.asList("固定资产", "消耗物品", "福利物品");

        List<Integer> count = new ArrayList<>();

        OutStoreExample outStoreExample = new OutStoreExample();
        outStoreExample.createCriteria().andTypeEqualTo("固定资产");
        count.add(outStoreMapper.countByExample(outStoreExample));

        OutStoreExample outStoreExample2 = new OutStoreExample();
        outStoreExample2.createCriteria().andTypeEqualTo("消耗物品");
        count.add(outStoreMapper.countByExample(outStoreExample2));

        OutStoreExample outStoreExample3 = new OutStoreExample();
        outStoreExample3.createCriteria().andTypeEqualTo("福利物品");
        count.add(outStoreMapper.countByExample(outStoreExample3));

        result.put("mapValue", count.toArray());
        result.put("mapName", type.toArray());
        return result;
    }
}