<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<aside>
    <a href="#">Brand</a>
    <div>
        <nav>
            <ul>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/mark">Input Mark</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/student">Student</a>
                </li>
                <li>
                    <a href="<%=request.getContextPath()%>/admin/instructor">Instructor</a>
                </li>
                <li>
                    <a href="#">Information</a>
                    <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/semester">Semester</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">Course Detail</a>
                    <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/course">Course</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">Major Detail</a>
                    <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/major">Major</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/class">Class</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">Account Information</a>
                    <ul>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/account">Account</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath()%>/admin/role">Role</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</aside>
