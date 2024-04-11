import sys
import urllib.request, urllib.parse
from bs4 import BeautifulSoup as bs

def toString(utf8String):
	return utf8String.encode('cp949', 'ignore').decode('cp949')

query = "ItemNewAll"
data = urllib.parse.urlencode({
	'ttbkey' : 'ttbasdf19161430002',
	'QueryType' : 'Title',
	'MaxResults':'50',
	'start':'1',
	'SearchTarget':'Book',
	'output':'xml',
	'Query':query,
	'inputencoding':'euc-kr',
	'Version':'20230901',
})
url = 'http://www.aladin.co.kr/ttb/api/ItemList.aspx'
con = urllib.request.urlopen(url +'?' + data)
objectXml = con.read()
con.close()

soup = bs(objectXml, 'html.parser')
soupString = str(soup)

if soupString.find('<error xml') > -1:
	for s in soup('errormessage'):
		print ('## Error!! ##')
		print ("Error Message: ", toString(s.contents[0]))
else:
	for s in soup('item'):
		print (toString(s.title.contents[0]), '-' , toString(s.link.contents[0]))