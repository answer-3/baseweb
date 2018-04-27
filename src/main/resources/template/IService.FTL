package ${package};

import com.lizhan.core.base.IBaseService;
import ${modelPackage}.${modelName};
<#if hasKeyEntity>
import ${modelPackage}.${modelName}Key;
</#if>
import ${modelPackage}.${modelName}Example;

public interface I${modelName}Service extends IBaseService<${hasKeyEntity?string(modelName+"Key","String")}, ${modelName}, ${modelName}Example> {

}
