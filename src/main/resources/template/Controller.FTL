package ${package};

import com.lizhan.core.base.BaseController;
import ${modelPackage}.${modelName};
<#if hasKeyEntity>
import ${modelPackage}.${modelName}Key;
</#if>
import ${modelPackage}.${modelName}Example;
import org.springframework.stereotype.Controller;

@Controller("${modelName}")
public class ${modelName}Controller extends BaseController<${hasKeyEntity?string(modelName+"Key","String")}, ${modelName}, ${modelName}Example> {

}
