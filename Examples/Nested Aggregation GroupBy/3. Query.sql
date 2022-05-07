SELECT HOUSENAME, COUNT(*) as StudentsAloneInRes, YEARSINRESIDENCE 
FROM RESIDENTINFO ri NATURAL JOIN  RESIDENTADDRESS ra 
WHERE YEARSINRESIDENCE IS NOT NULL AND NOT EXISTS 
									(SELECT * 
									FROM UNIT u, RESIDENTINFO ri2 NATURAL JOIN RESIDENTADDRESS ra2 
									WHERE ri.STUDENTNUMBER <> ri2.STUDENTNUMBER 
									AND ra.UNUMBER = ra2.UNUMBER 
									AND ra.FNUMBER = ra2.FNUMBER 
									AND ra.HOUSENAME = ra2.HOUSENAME 
									AND ra.RESZIPCODE = ra2.RESZIPCODE 
									AND ra.RESSTADDRESS = ra2.RESSTADDRESS)
 GROUP BY (HOUSENAME, YEARSINRESIDENCE) 
 HAVING (YEARSINRESIDENCE >= ALL (
 							SELECT YEARSINRESIDENCE 
 							FROM RESIDENTADDRESS NATURAL JOIN RESIDENTINFO NATURAL JOIN HOUSE 
 							WHERE RESIDENTINFO.YEARSINRESIDENCE IS NOT NULL 
 							AND ra.HOUSENAME = HOUSENAME))