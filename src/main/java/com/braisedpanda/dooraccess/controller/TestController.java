package com.braisedpanda.dooraccess.controller;

import com.braisedpanda.dooraccess.base.model.ObjectResponseStatus;
import com.braisedpanda.dooraccess.base.model.ResultPage;
import com.braisedpanda.dooraccess.base.model.TreeNode;
import com.braisedpanda.dooraccess.base.util.TreeUtil;
import com.braisedpanda.dooraccess.domian.ObjectResponse;
import com.braisedpanda.dooraccess.domian.po.Person;
import com.braisedpanda.dooraccess.domian.po.User;
import com.braisedpanda.dooraccess.utils.ExcelUtil;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class TestController {

    @RequestMapping("upload")
    public void updateExcel(@RequestBody MultipartFile file) {
        try{
            Workbook workbook = ExcelUtil.getWorkBook(file);//获取工作簿workbook
            Sheet sheetAt = workbook.getSheetAt(0);//根据工作簿获取整张excel表的信息
            List<Person> personList = new ArrayList<>();
            for (int i=1; i<= sheetAt.getLastRowNum(); i++){//第一行是表头，所以不要，i从1开始
                Person person = new Person();
               for(int j=0; j < sheetAt.getRow(i).getLastCellNum(); j++){//循环每一行

                    Cell cell = sheetAt.getRow(i).getCell(j);//获取每一个单元格的值
                    String value = ExcelUtil.getValue(cell);//把单元格的值转成字符串
                    if(j == 0){//第一列的信息
                        person.setId(value);
                        System.out.print("ID:"+value+" ");
                    }
                    if(j == 1){//第二列信息
                        person.setUsername(value);
                        System.out.print("姓名:"+value+" ");
                    }
                    if(j == 2){//第三列信息
                        person.setCategory(value);
                        System.out.print("部门:"+value+" ");
                    }

                }
               personList.add(person);
                System.out.println();
            }
            System.out.println("List:"+personList.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @RequestMapping("test")
    public void test(){
        System.out.println("123123123");
    }

    @RequestMapping("/toMain")
    public ModelAndView toMain(){
        return new ModelAndView("/main");
    }
    @RequestMapping("/toUserMgt")
    public ModelAndView toUserMgt(){
        return new ModelAndView("html/user-mgt/user-mgt/user-mgt");
    }


    @GetMapping("/tree")
    public ObjectResponseStatus getRoleMenuTree() {

        TreeNode treeNode = new TreeNode();
        treeNode.setOpen(true);
        treeNode.setName("测试");
        treeNode.setId(-1);
        List<TreeNode> treeNodeList = new ArrayList<>();

        TreeNode treeNode1 = new TreeNode();
        treeNode1.setName("子树");
        treeNode1.setId(2);
        treeNode1.setParentId(-1);
        treeNodeList.add(treeNode1);
        treeNode = treeNode.setChildren(TreeUtil.buildByRecursive(treeNodeList, -1));

        return ObjectResponseStatus.succeed(treeNode);
    }

    @RequestMapping("table/{nodeId}")
    public ObjectResponse pageList(@PathVariable( "nodeId") String nodeId){
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        if(nodeId.equals("-1") ){


            user1.setImei("1");
            user1.setIphone("123456");
            user1.setName("张三");
            user1.setPaytype("预付费");
            user1.setPc("2");
            user1.setTimes("2020-2-13 17:45:59");
            user1.setWater("20");
            user1.setYhlx("树3");
            user1.setZblx("电磁水表");
            user1.setZdylx("123");
            userList.add(user1);
        }
        if(nodeId.equals("2") ){


            user1.setImei("2");
            user1.setIphone("123456");
            user1.setName("李四");
            user1.setPaytype("后付费");
            user1.setPc("2");
            user1.setTimes("2020-2-13 17:45:59");
            user1.setWater("20");
            user1.setYhlx("树3");
            user1.setZblx("机械齿轮");
            user1.setZdylx("123");
            userList.add(user1);
        }

        ObjectResponse objectResponse = new ObjectResponse(0,userList);


        return objectResponse;
    }


}
