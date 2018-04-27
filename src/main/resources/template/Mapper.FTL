package ${package};

import com.lizhan.core.base.IBaseMapper;
import ${modelPackage}.${modelName};
<#if hasKeyEntity>
import ${modelPackage}.${modelName}Key;
</#if>
import ${modelPackage}.${modelName}Example;

public interface ${modelName}Mapper extends IBaseMapper<${hasKeyEntity?string(modelName+"Key","String")}, ${modelName}, ${modelName}Example> {

}
