/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.46.v20220331
 * Generated at: 2025-04-11 09:39:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.User;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1744169194824L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1744362821032L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.User");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("    ");

        String errorMessage = request.getParameter("error");
    
      out.write("\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Register</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css.css\">\r\n");
      out.write("        <style>\r\n");
      out.write("            /* Form Styling */\r\n");
      out.write("            a {\r\n");
      out.write("                color: #ff8a8a;\r\n");
      out.write("            }\r\n");
      out.write("            .register-form-container {\r\n");
      out.write("                background-color: white;\r\n");
      out.write("                padding: 2rem;\r\n");
      out.write("                border-radius: 8px;\r\n");
      out.write("                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);\r\n");
      out.write("                max-width: 500px;\r\n");
      out.write("                margin: 50px auto;\r\n");
      out.write("            }\r\n");
      out.write("            .form-group {\r\n");
      out.write("                margin-bottom: 1.5rem;\r\n");
      out.write("            }\r\n");
      out.write("            .form-group label {\r\n");
      out.write("                display: block;\r\n");
      out.write("                margin-bottom: 0.5rem;\r\n");
      out.write("                font-weight: 500;\r\n");
      out.write("            }\r\n");
      out.write("            .form-group input {\r\n");
      out.write("                width: 100%;\r\n");
      out.write("                padding: 0.8rem;\r\n");
      out.write("                border: 1px solid #ddd;\r\n");
      out.write("                border-radius: 4px;\r\n");
      out.write("                font-size: 1rem;\r\n");
      out.write("            }\r\n");
      out.write("            .checkbox-group {\r\n");
      out.write("                display: flex;\r\n");
      out.write("                align-items: flex-start;\r\n");
      out.write("                margin-bottom: 1.5rem;\r\n");
      out.write("            }\r\n");
      out.write("            .checkbox-group input {\r\n");
      out.write("                margin-top: 0.2rem;\r\n");
      out.write("                margin-left: 0.8rem;\r\n");
      out.write("            }\r\n");
      out.write("            .form-button {\r\n");
      out.write("                background-color: #F96E46;\r\n");
      out.write("                color: white;\r\n");
      out.write("                border: none;\r\n");
      out.write("                padding: 0.8rem 1.5rem;\r\n");
      out.write("                font-size: 1rem;\r\n");
      out.write("                border-radius: 4px;\r\n");
      out.write("                width: 100%;\r\n");
      out.write("                margin-bottom: 1rem;\r\n");
      out.write("            }\r\n");
      out.write("            .form-button:hover {\r\n");
      out.write("                background-color: #DDD;\r\n");
      out.write("            }\r\n");
      out.write("            .error-message {\r\n");
      out.write("                color: red;\r\n");
      out.write("                margin-bottom: 15px;\r\n");
      out.write("                text-align: center;\r\n");
      out.write("                font-weight: bold;\r\n");
      out.write("            }\r\n");
      out.write("        </style>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<header class=\"site-header\">\r\n");
      out.write("    <div class=\"header-container\">\r\n");
      out.write("        <div class=\"logo\">\r\n");
      out.write("            <a href=\"index.jsp\">IoTBay</a>\r\n");
      out.write("        </div>\r\n");
      out.write("       \r\n");
      out.write("        <div class=\"search-box\">\r\n");
      out.write("            <form action=\"search\" method=\"get\">\r\n");
      out.write("                <input type=\"text\" name=\"query\" placeholder=\"Search...\">\r\n");
      out.write("                <button type=\"submit\">Search</button>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("       \r\n");
      out.write("        <nav class=\"main-nav\">\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li><a href=\"products.jsp\">Products</a></li>\r\n");
      out.write("                ");

                    // Check if user is logged in
                    User headerUser = (User)session.getAttribute("user");
                    if (headerUser == null) {
                        // User is not logged in, show login and register links
                
      out.write("\r\n");
      out.write("                    <li><a href=\"register.jsp\">Register</a></li>\r\n");
      out.write("                ");
 } else {
                    // User is logged in, show logout button and username
                
      out.write("\r\n");
      out.write("                    <li><span>Welcome, ");
      out.print( headerUser.getFname() );
      out.write("</span></li>\r\n");
      out.write("                    <li><a href=\"LogoutServlet\" class=\"logout-button\">Logout</a></li>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("    </div>\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("    .site-header {\r\n");
      out.write("        background-color: #333;\r\n");
      out.write("        color: white;\r\n");
      out.write("        padding: 10px 0;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .header-container {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        justify-content: space-between;\r\n");
      out.write("        align-items: center;\r\n");
      out.write("        padding: 0 20px;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .logo {\r\n");
      out.write("        flex: 0 0 auto;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .logo a {\r\n");
      out.write("        color: white;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        font-size: 24px;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .search-box {\r\n");
      out.write("        flex: 0 0 auto;\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        left: 50%;\r\n");
      out.write("        transform: translateX(-50%);\r\n");
      out.write("        width: 300px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .search-box form {\r\n");
      out.write("        display: flex;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .search-box input[type=\"text\"] {\r\n");
      out.write("        flex-grow: 1;\r\n");
      out.write("        padding: 8px;\r\n");
      out.write("        border: none;\r\n");
      out.write("        border-radius: 4px 0 0 4px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .search-box button {\r\n");
      out.write("        background-color: #ff6c44;\r\n");
      out.write("        color: white;\r\n");
      out.write("        border: none;\r\n");
      out.write("        padding: 8px 15px;\r\n");
      out.write("        border-radius: 0 4px 4px 0;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav {\r\n");
      out.write("        flex: 0 0 auto;\r\n");
      out.write("        margin-left: auto;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav ul {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        list-style: none;\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav li {\r\n");
      out.write("        margin-left: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav a {\r\n");
      out.write("        color: white;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav a:hover {\r\n");
      out.write("        color: #ff6c44;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .main-nav span {\r\n");
      out.write("        color: #ff6c44;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .logout-button {\r\n");
      out.write("        background-color: #ff6c44;\r\n");
      out.write("        padding: 5px 10px;\r\n");
      out.write("        border-radius: 4px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    .logout-button:hover {\r\n");
      out.write("        background-color: #e55a33;\r\n");
      out.write("        color: white !important;\r\n");
      out.write("    }\r\n");
      out.write("</style>");
      out.write("<!-- Site title and nav please do not remove -->\r\n");
      out.write("       \r\n");
      out.write("        <main><!-- Please put all content inside the main tags -->\r\n");
      out.write("            <section class=\"register-form-container\">\r\n");
      out.write("                <h2>Register</h2>\r\n");
      out.write("                \r\n");
      out.write("                ");
 if (errorMessage != null) { 
      out.write("\r\n");
      out.write("                    <div class=\"error-message\">");
      out.print( errorMessage );
      out.write("</div>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                <form action=\"RegisterServlet\" method=\"post\">\r\n");
      out.write("                    <div class=\"form-group label\">\r\n");
      out.write("                        <label for=\"fname\">First Name: </label>\r\n");
      out.write("                        <input type=\"text\" id=\"fname\" name=\"fname\" required/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                   \r\n");
      out.write("                    <div class=\"form-group label\">\r\n");
      out.write("                        <label for=\"lname\">Last Name: </label>\r\n");
      out.write("                        <input type=\"text\" id=\"lname\" name=\"lname\" required/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"form-group label\">\r\n");
      out.write("                        <label for=\"email\">Email: </label>\r\n");
      out.write("                        <input type=\"text\" id=\"email\" name=\"email\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"form-group label\">\r\n");
      out.write("                        <label for=\"password\">Password: </label>\r\n");
      out.write("                        <input type=\"password\" id=\"password\" name=\"password\" required/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                   \r\n");
      out.write("                    <div class=\"checkbox-group\">\r\n");
      out.write("                        <label for=\"TOS\">TOS: </label>\r\n");
      out.write("                        <input type=\"checkbox\" id=\"TOS\" name=\"TOS\" required/>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <button type=\"submit\" class=\"form-button\">Register</button>\r\n");
      out.write("                    <div class=\"form-group label\">Already have an account? <a href=\"login.jsp\">Log in</a>.</div>\r\n");
      out.write("                </form>\r\n");
      out.write("            </section>\r\n");
      out.write("        </main>\r\n");
      out.write("       \r\n");
      out.write("        ");
      out.write("<footer>\n");
      out.write("    <div class=\"footer-content\">\n");
      out.write("            &copy; 2025 IoTBay. All rights reserved. | <a href=\"https://github.com/xJessD/iotbay\">GitHub Project Repo</a>             \n");
      out.write("    </div>\n");
      out.write("</footer>");
      out.write("<!-- Site footer please do not remove -->\r\n");
      out.write("    </body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
