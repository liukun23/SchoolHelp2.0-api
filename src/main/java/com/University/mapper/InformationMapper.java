package com.University.mapper;

import com.University.model.Information;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InformationMapper {
    @Select("select * from information_help order by information_id desc")
    public List<Information> Allinformation();
    /*     */
    @Select("select * from information_help where information_type=#{informationType}")
    public List<Information> InformationByType(@Param("informationType") int informationType);
    @Insert("insert into information_help(information_type,information_title,information_content,information_money,information_status,information_user,information_place,information_latitude,information_longitude,information_endtime,information_town,information_detaiLocation,information_limitSex,information_peopleNum,information_images) values(#{information.informationType},#{information.informationTitle},#{information.informationContent},#{information.informationMoney},0,#{information.informationUser},#{information.informationPlace},#{information.informationLatitude},#{information.informationLongitude},#{information.informationEndtime},#{information.informationTown},#{information.informationDetailocation},#{information.informationLimitSex},#{information.informationPeopleNum},#{information.informationImages})")
    public void addInformation(@Param("information") Information information);
    @Update("UPDATE information_help SET information_status='1' WHERE information_id=#{informationId}")
    public void CancelupdateInformationById(@Param("informationId") Integer informationId);
    @Update("UPDATE information_help SET information_status='0' WHERE information_id=#{informationId}")
    public void updateInfomation(@Param("informationId") Integer informationId);
    @Select("select information_peopleNum from information_help WHERE information_id=#{informationId}")
    public Integer selectpeopleNum(@Param("informationId") Integer informationId);
    @Select("select IFNULL(count(*),0) FROM information_number where information_id=#{informationId} GROUP BY information_id ;")
    public Integer countpeopleNum(@Param("informationId") Integer informationId);
    @Insert("insert into information_number(user_id,information_id) values(#{userId},#{informationId})")
    public void receiveInformation(@Param("informationId") Integer informationId,@Param("userId") Integer userId);
    @Select("select IFNULL(count(*),0) from information_number where information_id = #{informationId} AND user_id = #{userId}")
    public Integer SelectReceiveInformation(@Param("informationId")Integer informationId,@Param("userId")int userId);
    @Select("select * from information_help where information_id=#{InformationId}")
    public Information InformationById(@Param("InformationId") Integer informationId);
    @Delete("DELETE FROM information_number WHERE user_id=#{userId} and information_id=#{informationId}")
    public Integer CancelReceiveInforation(@Param("informationId")Integer informationId,@Param("userId")int userId);
}
