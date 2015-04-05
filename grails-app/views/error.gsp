<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
		<meta name="layout" content="main">
		<g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css"></g:if>
	</head>
	<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div id="normalPage">
					<div style="text-align: center;">
						<span style="color: rgb(197, 97, 82); font-size:150px;margin-right: -30px;"> 500 </span>
						<span style="color: rgb(226, 132, 104); font-size: 30px;">ERROR</span>
						<p>
							Internal server error.
						</p>

					</div>
				</div><!-- end normalPage -->

			</div>
			</div>
		</div>
	</body>
</html>
