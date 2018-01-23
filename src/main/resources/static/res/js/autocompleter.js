/**
 * 
 */

$(document).ready(function() {
		
        $(function() {
                $("#moviesearch").autocomplete({                	
                source : function(request, response) {
                $.ajax({
                        url : "/search",
                        type : "GET",
                        data : request,                        
                        dataType : "json",
                        success : function(data, textStatus, jqXHR) {
                        	console.log("success")
                        	 console.log( data);
                             //var items = data;
                             response(data);
                        },
                        error: function(jqXHR, textStatus, errorThrown){
                        	console.log("error");
                            console.log( errorThrown);
                       }
                });
        }
        });
        });
        
        /*$('#moviesearch').keyup(function(){
            var t = $('#moviesearch').val();
            $.ajax({
              type: "GET",
              url: "/search",
              data: {term: t},
              dataType: 'json',
              success: function( data ) {
                $('#tags').html('');
                for (var i in data) {
                  $('#tags').append(data[i] + '<br/>');
                }
              }
            });
          });*/
        $('#recommendBtn').click(function(){
        	var mov1 = $('#moviesearch').val();
        	var rating1 = $('#rating').val();
        	console.log(mov1 + '  ****** ' + rating1);
        	var movies =[];
        	movies.push({
        		"name" : mov1,
        		"rating" : rating1
        	});
        	
        	var movObj = {};
        	movObj.movies=movies;
        	
        	
        	$.ajax({
                url: "/recommend",
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(movObj),
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                	console.log(data);                	
	               	 //var myArr = $.parseJSON(data);
                	 var myArr = data;
	               	 var movielisthtml = "<ul>";
	               	 for (i = 0; i < myArr.length; i++) { 
	               		 movielisthtml += "<li>" + myArr[i] + "</li>";
	               	 }
	               	 movielisthtml += "</ul>";
	               	 $("#recommendDiv").removeClass("makeHidden").addClass("makeVisible");
	               	 $("#listDiv" ).append(movielisthtml);
                },
                error:function(data,status,er) {
                    console.log("error: "+data+" status: "+status+" er:"+er);
                }
                });
        }); 
        
});

