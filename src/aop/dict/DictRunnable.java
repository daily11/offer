//package aop.dict;
//
//import com.sunt.entity.TypeDictionaryDO;
//import com.sunt.mapper.TypeDictionaryMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
//
///**
// * **********************************************
// * <p/>
// * Date: 2018-10-16 13:17
// * <p/>
// * Author: SinPingWu
// * <p/>
// * Email: wuxinping@ubinavi.com.cn
// * <p/>
// * brief:
// * <p/>
// * history:
// * <p/>
// * **********************************************
// */
//@Component
//public class DictRunnable implements ApplicationRunner {
//
//    private TypeDictionaryMapper typeDictionaryMapper;
//    private HashMap<Integer, TypeDictionaryDO> dictKeyMap;
//    private HashMap<String, TypeDictionaryDO> dictValueMap;
//
//    private Date dictLatestUpdateDate = null;
//
//    private ReentrantReadWriteLock keyLock = new ReentrantReadWriteLock();
//    private ReadLock keyReadLock = keyLock.readLock();
//    private WriteLock keyWriteLock = keyLock.writeLock();
//
//    private ReentrantReadWriteLock valueLock = new ReentrantReadWriteLock();
//    private ReadLock valueReadLock = valueLock.readLock();
//    private WriteLock valueWriteLock = valueLock.writeLock();
//
//    @Autowired
//    public DictRunnable(TypeDictionaryMapper typeDictionaryMapper) {
//        this.typeDictionaryMapper = typeDictionaryMapper;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) {
//        List<TypeDictionaryDO> dictList = typeDictionaryMapper.listTypeDictionary(dictLatestUpdateDate);
//        dictLatestUpdateDate = dictList.get(dictList.size() - 1).getGmtModified();
//
//        HashMap<Integer, TypeDictionaryDO> dictMap = new HashMap<>();
//        for (TypeDictionaryDO typeDictionaryDO : dictList) {
//            dictMap.put(typeDictionaryDO.getTypeCode(), typeDictionaryDO);
//        }
//
//        HashMap<String, TypeDictionaryDO> dictValueMap = new HashMap<>();
//        for (TypeDictionaryDO typeDictionaryDO : dictList) {
//            dictValueMap.put(typeDictionaryDO.getTypeName(), typeDictionaryDO);
//        }
//
//        this.dictKeyMap = dictMap;
//        this.dictValueMap = dictValueMap;
//    }
//
//    /**
//     * 延迟10分钟执行, 10分钟间隔执行一次
//     */
//    //@Scheduled(/*initialDelay = 600000, */fixedRate = 10000)
//    @Scheduled(/*initialDelay = 600000, */ cron = "0 0/10 * * * ?")
//    public void updateDict() {
//        System.out.println("updateDict");
//        List<TypeDictionaryDO> dictList = typeDictionaryMapper.listTypeDictionary(dictLatestUpdateDate);
//        if (dictList == null || dictList.size() == 0)
//            return;
//
//        try {
//            keyWriteLock.lock();
//            for (TypeDictionaryDO typeDictionaryDO : dictList) {
//                dictKeyMap.put(typeDictionaryDO.getTypeCode(), typeDictionaryDO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            keyWriteLock.unlock();
//        }
//
//        try {
//            valueWriteLock.tryLock();
//            for (TypeDictionaryDO typeDictionaryDO : dictList) {
//                dictValueMap.put(typeDictionaryDO.getTypeName(), typeDictionaryDO);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            valueWriteLock.unlock();
//        }
//
//        dictLatestUpdateDate = dictList.get(dictList.size() - 1).getGmtModified();
//    }
//
//    public String getDictValue(int dictKey) {
//        if (dictKeyMap == null) {
//            return null;
//        }
//
//        if (!dictKeyMap.containsKey(dictKey)) {
//            return null;
//        }
//        TypeDictionaryDO typeDictionaryDO = null;
//        try {
//            keyReadLock.tryLock();
//            typeDictionaryDO = dictKeyMap.get(dictKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            keyReadLock.unlock();
//        }
//
//        if (typeDictionaryDO == null) {
//            return null;
//        }
//
//        return typeDictionaryDO.getTypeName();
//    }
//
//    public Integer getDictKey(String dictValue) {
//        if (dictValueMap == null) {
//            return null;
//        }
//
//        if (!dictValueMap.containsKey(dictValue)) {
//            return null;
//        }
//
//        TypeDictionaryDO typeDictionaryDO = null;
//        try {
//            valueReadLock.lock();
//            typeDictionaryDO = dictValueMap.get(dictValue);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            valueReadLock.unlock();
//        }
//
//        if (typeDictionaryDO == null) {
//            return null;
//        }
//
//        return typeDictionaryDO.getTypeCode();
//    }
//}
