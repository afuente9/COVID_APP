<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border = "1">
   	<th>Govern</th>
   	<th>vaccines</th>
   	<xsl:for-each select = "Administration">
   	<xsl:sort select = "@name"/>
   		<tr>
   			<td><i><xsl:value-of select="@name" /></i></td>
   			<td><xsl:value-of select="vaccines" /></td>
   		</tr>
   	</xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>