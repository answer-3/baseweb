package ${package};

import com.lizhan.core.base.BaseService;
import ${iServicePackage}.I${modelName}Service;
import ${modelPackage}.${modelName};
<#if hasKeyEntity>
import ${modelPackage}.${modelName}Key;
</#if>
import ${modelPackage}.${modelName}Example;
import org.springframework.stereotype.Service;

@Service
public class ${modelName}Service extends BaseService<${hasKeyEntity?string(modelName+"Key","String")}, ${modelName}, ${modelName}Example> implements I${modelName}Service {

}
