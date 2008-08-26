To build 
mvn clean install

To check the package multiple times
repeated-test.bat

To generate javadoc (generates it under target/site/apidocs/index.html)
mvn javadoc:javadoc

To generate code coverage (generates it under site\cobertura)
mvn cobertura:cobertura 

Please set these autoprops in svn for more info check out http://cwiki.apache.org/GMOxDEV/subversion-client-configuration.html
[auto-props]
*.cmd=text/plain
*.bat=svn:mime-type=text/plain
*.jpg=svn:mime-type=image/jpeg
*.jpeg=svn:mime-type=image/jpeg
*.png=svn:mime-type=image/png
*.tif=svn:mime-type=image/tiff
*.tiff=svn:mime-type=image/tiff
*.zip=svn:mime-type=application/zip
*.txt=svn:mime-type=text/plain
*.xml=svn:mime-type=text/xml
*.ent=svn:mime-type=text/plain
*.dtd=svn:mime-type=text/plain
*.vsl=svn:mime-type=text/plain
*.xsd=svn:mime-type=text/xml
*.xsl=svn:mime-type=text/xml
*.wsdl=svn:mime-type=text/xml
*.htm=svn:mime-type=text/html
*.html=svn:mime-type=text/html
*.css=svn:mime-type=text/css
*.js=svn:mime-type=text/plain
*.jsp=svn:mime-type=text/plain
*.txt=svn:mime-type=text/plain
*.java=svn:mime-type=text/plain
*.properties=svn:mime-type=text/plain
*.sql=svn:mime-type=text/plain