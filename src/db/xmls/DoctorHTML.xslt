<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Doctor</th>
      <th>Speciality</th>
      <th>Hospital</th>
      <th>Colegiate Number</th>
      <xsl:for-each select="DocList/docs">
      <xsl:sort select="@name" />
         <tr>
         	<td><i><xsl:value-of select="@name" /></i></td>
         	<td><xsl:value-of select="speciality" /></td>
         	<td><xsl:value-of select = "hospital" /></td>
         	<td><xsl:value-of select = "collegiate_number" /></td>
         </tr>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>