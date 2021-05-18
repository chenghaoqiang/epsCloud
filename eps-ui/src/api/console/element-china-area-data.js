// index.js
import DATA from './pro_city_dis'  // 省市区县数据源

const provinceData = DATA[100000] // provinceData拿到数据源中的全部的省份
const regionData = [] // 用于存放格式正确的省/市/区县数据，最终需要export出去

// 省
for (const provinceCode in provinceData) { // provinceCode拿到省份编码
  regionData.push({
    value: provinceCode,
    label: provinceData[provinceCode]
  })
}

// 市
for (const provinceObj of regionData) { // provinceObj拿到regionData中的省份对象
  // 需要给provinceObj添加children属性，以激活市级select框
  let city = []
  for (const cityCode in DATA[provinceObj.value]) { // DATA[provinceObj.value]拿到数据源所有的市，cityCode拿到所有市的编码
    city.push({
      value: cityCode,
      label: DATA[provinceObj.value][cityCode]
    })
    if (city.length) { // 排除掉不存在市的省份
      provinceObj.children = city // 添加children属性，激活市级select框
    }
  }
}

// 区县
for (const provinceObj of regionData) { // provinceObj拿到regionData中的省份对象
  let cityObj = provinceObj.children // cityObj拿到每个省份下的所有市
  if (cityObj) { // 因为“海外”选项没有市级，所有需要排除
    for (const city of cityObj) { // city拿到每个市
      // 给city添加children属性，以激活区/县select框
      let district = []
      for (const districtCode in DATA[city.value]) { // districtCode拿到对应市下所有区县
        district.push({
          value: districtCode,
          label: DATA[city.value][districtCode]
        })
      }
      if (district.length) {
        city.children = district
      }
    }
  }
}

export default regionData
