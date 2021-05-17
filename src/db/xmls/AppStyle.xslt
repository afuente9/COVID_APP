<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <p><b><xsl:value-of select="//name" /></b></p>
   <p><b>Contents: </b><xsl:value-of select="//content" /></p>
   <p><b>Paid authors:</b></p>
   <table border="1">
      <th>Laboratory</th>
      <th>Address</th>
      <th>CIF</th>
      <th>Vaccines produced</th>
      <th>Selected Patients</th>
      <xsl:for-each select="Lab">
      <xsl:sort select="@name" />
         <tr>
         	<td><i><xsl:value-of select="@name" /></i></td>
         	<td><xsl:value-of select="address" /></td>
         	<td><xsl:value-of select = "cif" /></td>
         	<td><xsl:value-of select = "vaccines_produce" /></td>
         	<td><xsl:value-of select = "//patients/name" /></td> 
         </tr>
      </xsl:for-each>
   </table>
   <table>
   	  <th>Country</th>
   	  <th>Total vaccines</th>
   	  <xsl:for-each  select = "Administration">
   	  <xsl:sort select = "@name">
   	  	<tr>
   	  		<td><i> <xsl:value-of select = "@name"/> </i></td>
         	<td> <xsl:value-of select = "vaccines"/> </td>
   	  	</tr>
   	  </xsl:sort>
   	  </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>