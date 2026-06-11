package com.cream.generator.config;

/**
 * TemplateConfig 配置类
 *
 * @author Cream
 * @since 2026-06-09 22:10
 */
public class TemplateConf {

    public static String controller = "/template/controller/controller.java.vm";

    public static String service = "/template/service/service.java.vm";

    public static String serviceImpl = "/template/service/impl/serviceImpl.java.vm";

    public static String mapper = "/template/mapper/mapper.java.vm";

    public static String mapperXml = "/template/mapper/xml/mapper.xml.vm";

    public static String entity = "/template/entity/po/entity.java.vm";

    /*
     * dto
     */
    public static String saveDto = "/template/entity/dto/save/saveDto.java.vm";

    public static String updateDto = "/template/entity/dto/update/updateDto.java.vm";

    public static String queryPageDto = "/template/entity/dto/query/queryPageDto.java.vm";

    public static String queryDto = "/template/entity/dto/query/queryDto.java.vm";

    /*
     * vo
     */
    public static String pageVo = "/template/entity/vo/pageVo.java.vm";

    public static String listVo = "/template/entity/vo/listVo.java.vm";

    public static String vo = "/template/entity/vo/vo.java.vm";

}
