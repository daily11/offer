//package aop.dict;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
///**
// * **********************************************
// * <p/>
// * Date: 2018-10-16 11:19
// * <p/>
// * Author: SinPingWu
// * <p/>
// * Email: wuxinping@ubinavi.com.cn
// * <p/>
// * brief: 填充字典表中的内容至对象的属性。该切面在service层的方法中调用。
// * <p/>
// * history:
// * <p/>
// * **********************************************
// */
//@Component(value = "fillDictValueAopHandler")
//@Aspect
//public class FillDictValueAopHandler {
//
//    private DictRunnable dictRunnable;
//    private String[] basicTypeArray;
//
//    public FillDictValueAopHandler(DictRunnable dictRunnable) {
//        this.dictRunnable = dictRunnable;
//        basicTypeArray = new String[] {"Boolean", "boolean", "String", "Character", "Char",
//                "char", "Short", "short", "Double", "double", "Long", "long", "Float", "float",
//                "Integer", "int", "Byte", "byte", "Void", "Date", "BigDecimal"};
//    }
//
//
//    private final String ExpGetResultDataPoint = "execution(* com.sunt.service..*.*(..))";
//
//    /**
//     * 切面方法 （修改目标方法返回值带有xxxName的字段）
//     */
//    @Around(ExpGetResultDataPoint)
//    public Object parameterCheck(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object target = joinPoint.getTarget();//返回被织入增强处理的目标对象       getThis：返回AOP框架为目标对象生成的代理对象
//        Object[] args = joinPoint.getArgs(); //获取目标对象方法参数
//
//        //执行方法，以新的参数（如果不带args就是用原先的参数；这里带不带都可以是，上面方法获取原先参数的引用做的修改）
//        Object returnValue = joinPoint.proceed(args);
//        if (returnValue != null) {
//            try {
//                //修改参数
//                returnValue = changeValue(returnValue);
//            } catch (Exception e) {
//                System.out.println("修改目标方法返回值异常。目标类："
//                        + target.getClass() + "方法：" + joinPoint.getSignature().getName() + "修改的值："
//                        + returnValue);
//            }
//        }
//        return returnValue;
//    }
//
//    /**
//     * 修改对象xxxName字段的值
//     */
//    private Object changeValue(Object _obj) throws Exception {
//        if (_obj == null)
//            return null;
//
//        //基本类型不作操作
//        if (_obj.getClass().isPrimitive()) {
//            return _obj;
//        }
//        if (!isObject(_obj.getClass().getTypeName())) {
//            return _obj;
//        }
//
//        if (_obj instanceof Map) {
//            changeMapValue(_obj);
//        } else if (_obj instanceof List) {
//            @SuppressWarnings("unchecked")
//            List<Object> list = (List<Object>) _obj;
//            for (Object obj : list) {
//                if (obj instanceof Map) {
//                    changeMapValue(obj);
//                } else {
//                    changeObjectValue(obj);
//                }
//            }
//        } else {
//            changeObjectValue(_obj);
//        }
//        return _obj;
//    }
//
//    private void changeMapValue(Object _obj) throws Exception {
//        /*Map<String, Object> map = (Map<String,Object>) _obj;
//
//        if(map.containsKey(HANDLE_FIELD_NAME)) {
//            Object fieldValue = map.get(HANDLE_FIELD_NAME);
//            String afterValue = crypto(fieldValue, flag);
//            if(!ObjectIsNullUtil.isNullOrEmpty(afterValue)){
//                map.put(HANDLE_FIELD_NAME, afterValue);
//            }
//        }
//        return _obj;*/
//    }
//
//
//
//    private void changeObjectValue(Object _obj) throws Exception {
//        Class<?> resultClz = _obj.getClass();
//
//        // 获取所有私有属性至fieldList列表中
//        ArrayList<Field> fieldList = new ArrayList<>();
//        getAllField(resultClz, fieldList);
//
//        ArrayList<Field> objFieldList = new ArrayList<>();
//
//        for (Field field : fieldList) {
//            if (isObject(field.getType().getTypeName())) {
//                objFieldList.add(field);
//                continue;
//            }
//
//            if (!isNeedInjectionDictionaryValueForField(field)) {
//                continue;
//            }
//
//            field.setAccessible(true);
//            String fieldName = field.getName();
//            String keyFieldName = fieldName.substring(0, fieldName.lastIndexOf("Name"));
//
//            Integer dictKey = getFieldValue(_obj, keyFieldName);
//            if (dictKey == null) {
//                continue;
//            }
//
//            String dictValue = getDictValue(dictKey);
//            if (dictValue != null) {
//                field.set(_obj, dictValue);
//            }
//        }
//
//        /*for (Field field : objFieldList) {
//            field.setAccessible(true);
//            Object fieldContentObj = field.get(_obj);
//
//            changeValue(fieldContentObj);
//        }*/
//    }
//
//    private boolean isNeedInjectionDictionaryValueForField(Field field) {
//        if (field == null) {
//            return false;
//        }
//
//        Annotation[] annotations = field.getAnnotations();
//
//        for (Annotation annotation : annotations) {
//            if (!annotation.annotationType().isAssignableFrom(Dictionary.class)) {
//                continue;
//            }
//
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean isObject(String fieldTypeName) {
//        if (fieldTypeName.contains(".")) {
//            fieldTypeName = fieldTypeName.substring(fieldTypeName.lastIndexOf(".") + 1);
//        }
//
//        List<String> basicTypeList = Arrays.asList(basicTypeArray);
//
//        return !basicTypeList.contains(fieldTypeName);
//    }
//
//    private Integer getFieldValue(Object _obj, String fieldName)
//            throws IllegalAccessException {
//        Class<?> resultClz = _obj.getClass();
//
//        // 获取所有私有属性至fieldList列表中
//        ArrayList<Field> fieldList = new ArrayList<>();
//        getAllField(resultClz, fieldList);
//
//        for (Field field : fieldList) {
//            String itemFieldName = field.getName();
//            if (fieldName.equalsIgnoreCase(itemFieldName)) {
//                field.setAccessible(true);
//                Object fieldValueObject = field.get(_obj);
//                return (Integer) fieldValueObject;
//            }
//        }
//
//        return 0;
//    }
//
//    /**
//     * 获取{@code resultClz}中所有的私有属性。
//     */
//    private void getAllField(Class<?> resultClz, ArrayList<Field> fieldList) {
//        while (resultClz != null) {
//            fieldList.addAll(Arrays.asList(resultClz.getDeclaredFields()));
//            resultClz = resultClz.getSuperclass();
//        }
//    }
//
//    private String getDictValue(int dictKey) {
//        return dictRunnable.getDictValue(dictKey);
//    }
//
//}