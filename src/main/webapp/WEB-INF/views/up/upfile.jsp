<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
</head>
<body>
<h2>上传文件</h2>
<form action="fileSave" method="post"  enctype="multipart/form-data">
  <p>
     <label for="files">文件：</label>
     <input type="file" name="files" id="files" multiple="multiple" />
   </p>
   <p>
   <button>提交</button>
   </p>
   <p>
     ${message}
   </p>
</form>
</body>
</html>