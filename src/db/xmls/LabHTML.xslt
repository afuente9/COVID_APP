<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <table border="1">
      <th>Laboratory</th>
      <th>Address</th>
      <th>CIF</th>
      <th>Vaccines produced</th>
      <th>Selected Patients</th>
      <xsl:for-each select="LabList/labs">
      <xsl:sort select="@name" />
         <tr>
         	<td><i><xsl:value-of select="@name" /></i></td>
         	<td><xsl:value-of select="address" /></td>
         	<td><xsl:value-of select = "cif" /></td>
         	<td><xsl:value-of select = "vaccines_produce" /></td>
         </tr>
         <xsl:for-each select="LabList/labs/Patients/Patient">
		 <xsl:sort select = "@name"/>
			<tr>
				<td><i><xsl:value-of select = "@name"/></i></td>
			</tr>	 	
     	 </xsl:for-each>
      </xsl:for-each>
   </table>
   </html>
</xsl:template>

</xsl:stylesheet>