/*
 * Copyright 2008-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl2.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kfs.module.cam.fixture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kuali.kfs.module.cam.businessobject.BarcodeInventoryErrorDetail;
import org.kuali.kfs.sys.context.SpringContext;
import org.kuali.rice.krad.service.BusinessObjectService;

//@Transactional

public enum BarcodeInventoryServiceFixture{

    DATA();
    private BusinessObjectService businessObjectService;    
    private int testDataPos;
    private static Properties properties;
    static {
        String propertiesFileName = "org/kuali/kfs/module/cam/document/service/barcode_inventory_service.properties";
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(propertiesFileName));
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static String TEST_RECORD="testRecord";
    static String BCIE="bcie";
    static String FIELD_NAMES="fieldNames";
    static String NUM_OF_REC="numOfRecords";
    static String DELIMINATOR="deliminator";
    
    private BarcodeInventoryServiceFixture() {  
        businessObjectService = SpringContext.getBean(BusinessObjectService.class);        
    }

    @SuppressWarnings("deprecation")
    public List<BarcodeInventoryErrorDetail> getBarcodeInventoryDetail() {
        Integer numOfRecords = new Integer(properties.getProperty(BCIE+"."+NUM_OF_REC));                        
        List<BarcodeInventoryErrorDetail> details = new ArrayList<BarcodeInventoryErrorDetail>();
                
        String deliminator = properties.getProperty(DELIMINATOR);
        String fieldNames = properties.getProperty(BCIE+"."+FIELD_NAMES);

        for(int i=1;i<=numOfRecords.intValue();i++) {
            String propertyKey = BCIE+"."+TEST_RECORD + i;
            details.add(CamsFixture.DATA_POPULATOR.buildTestDataObject(BarcodeInventoryErrorDetail.class, properties, propertyKey, fieldNames, deliminator));
        }
        return details;
    }
}
