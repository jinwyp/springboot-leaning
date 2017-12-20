# Spring Boot learning


## 项目特点

1. 通过配置json文件 定义业务4xx类型的错误信息, 配置文件在 resource/errorMessage.json。 好处是容易定制并可以直接给复制代码给前端使用。 
使用方式: 通过 ```throw new BusinessException("commentRequired")``` 就是参数是json配置文件的key的名称 ; 

2. 错误处理完善, 在 GlobalApiControllerExceptionHandler 里面处理了常用的表单提交的错误, 并且根据不同异常类型返回详细的错误信息。

3. spring-boot-starter-data-rest 自动生成基础的CRUD 接口, 并返回数据中带有数据ID字段.

4. spring-boot-devtool 自动编译

5. 友好的异常信息显示, 4xx错误只显示本项目的异常, 隐藏了冗长的spring框架的异常, 5xx 错误可以控制显示多少行异常信息.

6. 去掉了默认的404




## 问题

1. 无法通过 request header 的 Content-Type 类型返回页面还是json.   

- Content-Type: 一般为三类 第一种 默认 text/html; charset=utf-8, 数据类型是其他，一般返回html页面, 第二种是 application/json 提交数据是json 第三方是表单 常见的两种是 application/x-www-form-urlencoded 和 multipart/form-data 代表用户表单提交或上传文件.

- 例如 如果是ajax 请求, content-type是 application/json 这时无论请求是200 或 400 或 500 错误 都是返回json数据. 
例如 请求 200 时 返回 
```json
{
  "status" : true,
  "data" : {
    "id" : "5a3793b3aab32b55347edc46",
    "name" : "jinwyp1",
    "email" : "jinwyp1@163.com",
    "firstName" : null,
    "lastName" : "dddd",
    "_links" : {
      "self" : {
        "href" : "http://localhost:8090/api/users/5a3793b3aab32b55347edc46"
      },
      "websiteUser" : {
        "href" : "http://localhost:8090/api/users/5a3793b3aab32b55347edc46"
      },
      "roleList" : {
        "href" : "http://localhost:8090/api/users/5a3793b3aab32b55347edc46/roleList"
      }
    }
  }
}

```

请求 404时返回 
```json
{
  "status": false,
  "error": {
    "code": 404,
    "message": "No handler found for GET /api/pers",
    "field": null,
    "exceptionName": "org.springframework.web.servlet.NoHandlerFoundException",
    "url": "http://localhost:8090/api/pers?id=100&comment=Person"
  }
}

```

请求 500时返回 
```json
{
  "status": false,
  "error": {
    "code": 500,
    "message": "发生错误",
    "field": null,
    "exceptionName": "java.lang.Exception",
    "url": "http://localhost:8090/api/err?id=100"
  }
}

```

- Content-Type: 是 text/html; charset=utf-8, 根据200 ,400, 404 , 500 返回不同的页面, 使用不同thymeleaf view模版。 

- @ControllerAdvice 是个全局异常处理 很难解决这个问题, 只能返回html或json, 无法根据类型不同 返回不同的格式. 尤其是404的时候的不同处理. 试过用ControllerAdvice限制不同的包名, 效果达不到.



2. 控制台日志LOG 显示 request header 和 response status , body

- 目前 通过新版本 1.5.8 CommonsRequestLoggingFilter 仅能显示request header 和 请求的信息, 无法显示response 信息. 
- CommonsRequestLoggingFilter 无法定制想要的格式 只能显示在一行上 例如 url参数请求显示为 ===== Request : [uri=/api/err?id=100], post body 显示为 ===== Request : [uri=/api/article;payload=comment=Ce33&id=111]
- 错误异常信息定制和美化显示, 目前只做到了过滤某个包名的错误, 例如只显示该项目的错误不包括spring的异常,这样方便看,默认异常显示太多,无用信息太多。 但无法美化, 例如 像nodejs库这样的高亮和空行 [pretty-error]https://github.com/AriaMinaei/pretty-error
- 无法显示像 nodejs koa2 或PHP 框架这种友好的请求信息显示, 包括状态码和请求耗时.
```
  <-- POST /api/user/login
  xxx POST /api/user/login 400 374ms -
  
   { request:
     { method: 'POST',
       url: '/api/user/login',
       header:
        { cookie: 'Webstorm-cb85b0bd=1f43e204-ec6b-4f5b-b2ba-310965342509; platform=3382e88c-9cc9-4465-84e6-3ec90c08a942; koa2:session=NdtM~ynrDJuITF_I7ONVk; koa2:session.sig=bWB-hQYKuDUl4_00-ByS_eem5rI',
          'accept-language': 'en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7,zh-TW;q=0.6,ja;q=0.5,it;q=0.4',
          'accept-encoding': 'gzip, deflate, br',
          referer: 'http://localhost:4210/web/login',
          'content-type': 'application/json',
          'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36',
          origin: 'http://localhost:4210',
          accept: 'application/json, text/plain, */*',
          'content-length': '42',
          connection: 'close',
          host: 'localhost:4210' } },
    response:
     { status: 400,
       message: 'Bad Request',
       header:
        { 'x-content-type-options': 'nosniff',
          'x-dns-prefetch-control': 'off',
          'x-frame-options': 'SAMEORIGIN',
          'strict-transport-security': 'max-age=15552000; includeSubDomains',
          'x-download-options': 'noopen',
          'x-xss-protection': '1; mode=block',
          vary: 'Origin',
          'access-control-allow-origin': 'http://localhost:4210',
          'content-type': 'application/json; charset=utf-8' } },
    app: { subdomainOffset: 2, proxy: true, env: 'development' },
    originalUrl: '/api/user/login',
    req: '<original node req>',
    res: '<original node res>',
    socket: '<original node socket>' } +0ms
  ==================== ctx.status 400
  
```



3. mysql 连表查询 JAVA直接返回结果. JPA好像可以做到,需要研究.

- 例如 user 表 三个字段 id, username, userrole. userrole 表两个字段 id, name. 

userrole 记录两条如下 
```json
    {
      "id": 1, 
      "name" : "普通用户"
    }
```
```json
    {
      "id" : 2, 
      "name" : "管理员"
    }
```

user 记录一条如下 

```json
    {
      "id" : 1, 
      "username" : "test1",
      "userole": 1
    }
```

希望获取用户返回的数据数据结构是自动把userrole 的所有信息带出来  

例如返回的是这样, userrole 字段 原本在表里储存的是ID, 但返回的是一个对象. 而且在userrole属性下包了一层, 现在都是手写的sql 都是平级放置, 关键字段名称可能命名的不是原来的字段名例如 userrole.id 变成了roleId或userroleId.
```json
    {
      "id" : 1, 
      "username" : "test1",
      "userole": {
               "id": 1, 
               "name" : "普通用户"
             }
    }
```

还有如果是一对多或多对多关系 userrole 返回的可能是数组
```json
    {
      "id" : 1, 
      "username" : "test1",
      "userole": [
        {
               "id": 1, 
               "name" : "普通用户"
             },
        {
               "id" : 2, 
               "name" : "管理员"
             }
             ]
    }
```

- 上面的例子是两张表, 还有多张表的很常见情况, 例如 业务关联用户, 用户关联角色. 能自动输出表的所有字段信息. 并带有层级关系.

4. mysql 异常 

5. mongodb 异常

