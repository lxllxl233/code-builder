# code-builder

使用步骤

- 修改 properties 配置文件

  >![https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601707866703选区_113.png](https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601707866703选区_113.png)

- 修改主配置

  >![https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601707985448选区_114.png](https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601707985448选区_114.png)

- 运行即可！！！

可以修改生成文件的模板，模板文件在 resource/template 文件夹下

修改模板(可以根据自己需求更改模板，模板更改规则如下)

>主要分为 controller,service,serviceImpl,dao,entity,vo等，下面以controller前缀为例
>
>package语句
>
>>```temp
>>${controllerPackage}
>>```
>
>import语句
>
>>```temp
>>${controllerImport}
>>```
>
>实体类名
>
>>```temp
>>${controllerName}
>>```
>
>实体类变量名
>
>>```temp
>>${controllerParamName}
>>```
>
>全限定类名
>
>>```temp
>>${controllerPackagePath}
>>```

![https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601708201945OIP.jpeg](https://woqi-younai.oss-cn-beijing.aliyuncs.com/avatar/1601708201945OIP.jpeg)

完善工程可以直接提交 pull request

联系作者 : QQ-227666920

