<html>
    <% 
        // Variables go here
    %>

    <header>
        <title>Log Out</title>
        <link rel="stylesheet" href="css.css">
         
    </header>

    <body>
            <%@ include file="header.jsp" %><!-- Site title and nav please do not remove -->

        
        <main><!-- Please put all content inside the main tags -->
            <section>
                <%session.invalidate() ;%>

                <p>You are now logged out. Click here to go to <a href="login.jsp">home</a>.</p>
            </section>
                
        </main>
        

            <%@ include file="footer.jsp" %><!-- Site footer please do not remove -->
    </body>
</html>
