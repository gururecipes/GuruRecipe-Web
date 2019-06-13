<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TARIF EKLE</title>
    </head>
    <body>
    <center> <h1><u> YENI TARIFINIZI OLUSTURUN</u></h1> </center> 
    <center>
        <div>
            <form action="addRecipe" method="POST">
                <table>
                    <tr>
                        <td>Tarif Adi</td>
                        <td><input type="text" class="form-control" name="recipeName" placeholder="recipeName"></td>
                    </tr>
                    <tr>
                        <td>Icerik</td>
                        <td><input type="content" class="form-control" name="content" placeholder="content"></td>                         
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input class="btn btn-success" type="submit" value="Tarifi Ekle"</td>
                    </tr>
                    
                    
                </table>                 
            </form>
            
        </div>
    </center>
    </body>
</html>