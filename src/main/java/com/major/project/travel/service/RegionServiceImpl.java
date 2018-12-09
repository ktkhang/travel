package com.major.project.travel.service;

import com.major.project.travel.dao.PlaceDao;
import com.major.project.travel.dao.PlaceUserDao;
import com.major.project.travel.dao.RegionDao;
import com.major.project.travel.dao.UserRegionDao;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.RegionRequest;
import com.major.project.travel.response.PlaceUserResponse;
import com.major.project.travel.response.RegionUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUY on 10/14/2018
 */
@Transactional
@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Autowired
    private PlaceDao placeDao;

    @Autowired
    private UserRegionDao userRegionDao;

    @Autowired
    private PlaceUserDao placeUserDao;

    @Override
    public Region create(RegionRequest regionRequest) {
        Region region = new Region();
        region.setId(regionRequest.getId());
        region.setName(regionRequest.getName());
        region.setTitle(regionRequest.getTitle());
        regionDao.saveObj(region);
        return region;
    }

    @Override
    public List<Region> list() {
        return regionDao.findAll();
    }

    @Override
    public Region findRegionByUid(String uid) throws DataNotFoundException {
        Region region = regionDao.findRegionByUid(uid);
        if(region == null){
            throw new RestException(String.format("Region have {%s} is not existed.", String.format("regionUid = %s", uid)),HttpServletResponse.SC_NOT_FOUND);
        }
        return regionDao.findRegionByUid(uid);
    }

    @Override
    public List<Region> findByUser(User user) throws DataNotFoundException {
        return regionDao.findByUser(user);
    }

    @Override
    public Region findByPlace(Place place) throws DataNotFoundException {
        return regionDao.findByPlace(place);
    }

    @Override
    public List<RegionUserResponse> findAllByUser(User user) {
        List<Region> allRegions = regionDao.findAll();
        List<RegionUserResponse> regionUserResponse = new ArrayList<RegionUserResponse>();
        for(Region region : allRegions){
            RegionUserResponse regionResponse = new RegionUserResponse();

            regionResponse.setCreatedDate(region.getCreatedDate());
            regionResponse.setCreatedBy(region.getCreatedBy());
            regionResponse.setUpdatedDate(region.getUpdatedDate());
            regionResponse.setUpdatedBy(region.getUpdatedBy());
            regionResponse.setId(region.getId());
            regionResponse.setUid(region.getUid());
            regionResponse.setName(region.getName());
            regionResponse.setTitle(region.getTitle());
            regionResponse.setUserRegions(region.getUserRegions());

            UserRegion regionUserDetail = userRegionDao.findByUserAndRegion(user, region);
            regionResponse.setRegionUserDetail(regionUserDetail);

            List<PlaceUserResponse> placeList = new ArrayList<PlaceUserResponse>();
            List<Place> places = region.getPlaceList();
            for(Place place : places){
                PlaceUserResponse placeResponse = new PlaceUserResponse();

                placeResponse.setCreatedDate(place.getCreatedDate());
                placeResponse.setCreatedBy(place.getCreatedBy());
                placeResponse.setUpdatedDate(place.getUpdatedDate());
                placeResponse.setUpdatedBy(place.getUpdatedBy());
                placeResponse.setId(place.getId());
                placeResponse.setUid(place.getUid());
                placeResponse.setName(place.getName());
                placeResponse.setSvgPath(place.getSvgPath());
                placeResponse.setTitle(place.getTitle());
                placeResponse.setLatitude(place.getLatitude());
                placeResponse.setLongitude(place.getLongitude());
                placeResponse.setPlaceStatus(place.getPlaceStatus());
                placeResponse.setPlaceUsers(place.getPlaceUsers());

                PlaceUser placeUserDetail = placeUserDao.findByUserAndPlace(user, place);
                placeResponse.setPlaceUserDetail(placeUserDetail);

                placeList.add(placeResponse);
            }
            regionResponse.setPlaceList(placeList);

            regionUserResponse.add(regionResponse);
        }

        return regionUserResponse;
    }

    @Override
    public List<Region> createSampleData() {
        Region region1 = new Region();
        region1.setId("VN-01");
        region1.setName("LaiChau");
        region1.setTitle("Lai Châu, Việt Nam");
        regionDao.saveObj(region1);

        Region region2 = new Region();
        region2.setId("VN-02");
        region2.setName("LaoCai");
        region2.setTitle("Lào Cai, Việt Nam");
        regionDao.saveObj(region2);

        Region region3 = new Region();
        region3.setId("VN-03");
        region3.setName("HaGiang");
        region3.setTitle("Hà Giang, Việt Nam");
        regionDao.saveObj(region3);

        Region region4 = new Region();
        region4.setId("VN-04");
        region4.setName("CaoBang");
        region4.setTitle("Cao Bằng, Việt Nam");
        regionDao.saveObj(region4);

        Region region5 = new Region();
        region5.setId("VN-05");
        region5.setName("SonLa");
        region5.setTitle("Sơn La, Việt Nam");
        regionDao.saveObj(region5);

        Region region6 = new Region();
        region6.setId("VN-06");
        region6.setName("YenBai");
        region6.setTitle("Yên Bái, Việt Nam");
        regionDao.saveObj(region6);

        Region region7 = new Region();
        region7.setId("VN-07");
        region7.setName("TuyenQuang");
        region7.setTitle("Tuyên Quang, Việt Nam");
        regionDao.saveObj(region7);

        Region region8 = new Region();
        region8.setId("VN-08");
        region8.setName("LangSon");
        region8.setTitle("Lạng Sơn, Việt Nam");
        regionDao.saveObj(region8);

        Region region9 = new Region();
        region9.setId("VN-09");
        region9.setName("QuangNinh");
        region9.setTitle("Quảng Ninh, Việt Nam");
        regionDao.saveObj(region9);

        Region region10 = new Region();
        region10.setId("VN-10");
        region10.setName("HoaBinh");
        region10.setTitle("Hòa Bình, Việt Nam");
        regionDao.saveObj(region10);

        Region region11 = new Region();
        region11.setId("VN-11");
        region11.setName("NinhBinh");
        region11.setTitle("Ninh Bình, Việt Nam");
        regionDao.saveObj(region11);

        Region region12 = new Region();
        region12.setId("VN-12");
        region12.setName("ThaiBinh");
        region12.setTitle("Thái Bình, Việt Nam");
        regionDao.saveObj(region12);

        Region region13 = new Region();
        region13.setId("VN-13");
        region13.setName("ThanhHoa");
        region13.setTitle("Thanh Hóa, Việt Nam");
        regionDao.saveObj(region13);

        Region region14 = new Region();
        region14.setId("VN-14");
        region14.setName("NgheAn");
        region14.setTitle("Nghệ An, Việt Nam");
        regionDao.saveObj(region14);

        Region region15 = new Region();
        region15.setId("VN-15");
        region15.setName("HaTinh");
        region15.setTitle("Hà Tĩnh, Việt Nam");
        regionDao.saveObj(region15);

        Region region16 = new Region();
        region16.setId("VN-16");
        region16.setName("QuangBinh");
        region16.setTitle("Quảng Bình, Việt Nam");
        regionDao.saveObj(region16);

        Region region17 = new Region();
        region17.setId("VN-17");
        region17.setName("QuangTri");
        region17.setTitle("Quảng Trị, Việt Nam");
        regionDao.saveObj(region17);

        Region region18 = new Region();
        region18.setId("VN-18");
        region18.setName("ThuaThienHue");
        region18.setTitle("Thừa Thiên–Huế, Việt Nam");
        regionDao.saveObj(region18);

        Region region19 = new Region();
        region19.setId("VN-19");
        region19.setName("QuangNam");
        region19.setTitle("Quảng Nam, Việt Nam");
        regionDao.saveObj(region19);

        Region region20 = new Region();
        region20.setId("VN-20");
        region20.setName("KonTum");
        region20.setTitle("Kon Tum, Việt Nam");
        regionDao.saveObj(region20);

        Region region21 = new Region();
        region21.setId("VN-21");
        region21.setName("QuangNgai");
        region21.setTitle("Quảng Ngãi, Việt Nam");
        regionDao.saveObj(region21);

        Region region22 = new Region();
        region22.setId("VN-22");
        region22.setName("GiaLai");
        region22.setTitle("Gia Lai, Việt Nam");
        regionDao.saveObj(region22);

        Region region23 = new Region();
        region23.setId("VN-23");
        region23.setName("BinhDinh");
        region23.setTitle("Bình Định, Việt Nam");
        regionDao.saveObj(region23);

        Region region24 = new Region();
        region24.setId("VN-24");
        region24.setName("PhuYen");
        region24.setTitle("Phú Yên, Việt Nam");
        regionDao.saveObj(region24);

        Region region25 = new Region();
        region25.setId("VN-25");
        region25.setName("DakLak");
        region25.setTitle("Đắk Lắk, Việt Nam");
        regionDao.saveObj(region25);

        Region region26 = new Region();
        region26.setId("VN-26");
        region26.setName("LamDong");
        region26.setTitle("Lâm Đồng, Việt Nam");
        regionDao.saveObj(region26);

        Region region27 = new Region();
        region27.setId("VN-27");
        region27.setName("NinhThuan");
        region27.setTitle("Ninh Thuận, Việt Nam");
        regionDao.saveObj(region27);

        Region region28 = new Region();
        region28.setId("VN-28");
        region28.setName("TayNinh");
        region28.setTitle("Tây Ninh, Việt Nam");
        regionDao.saveObj(region28);

        Region region29 = new Region();
        region29.setId("VN-29");
        region29.setName("DongNai");
        region29.setTitle("Đồng Nai, Việt Nam");
        regionDao.saveObj(region29);

        Region region30 = new Region();
        region30.setId("VN-30");
        region30.setName("BinhThuan");
        region30.setTitle("Bình Thuận, Việt Nam");
        regionDao.saveObj(region30);

        Region region31 = new Region();
        region31.setId("VN-31");
        region31.setName("LongAn");
        region31.setTitle("Long An, Việt Nam");
        regionDao.saveObj(region31);

        Region region32 = new Region();
        region32.setId("VN-32");
        region32.setName("BaRiaVungTau");
        region32.setTitle("Bà Rịa–Vũng Tàu, Việt Nam");
        regionDao.saveObj(region32);

        Region region33 = new Region();
        region33.setId("VN-33");
        region33.setName("AnGiang");
        region33.setTitle("An Giang, Việt Nam");
        regionDao.saveObj(region33);

        Region region34 = new Region();
        region34.setId("VN-34");
        region34.setName("DongThap");
        region34.setTitle("Đồng Tháp, Việt Nam");
        regionDao.saveObj(region34);

        Region region35 = new Region();
        region35.setId("VN-35");
        region35.setName("TienGiang");
        region35.setTitle("Tiền Giang, Việt Nam");
        regionDao.saveObj(region35);



        Region region36 = new Region();
        region36.setId("VN-36");
        region36.setName("KienGiang");
        region36.setTitle("Kiên Giang, Việt Nam");
        regionDao.saveObj(region36);

        Region region37 = new Region();
        region37.setId("VN-37");
        region37.setName("VinhLong");
        region37.setTitle("Vĩnh Long, Việt Nam");
        regionDao.saveObj(region37);

        Region region38 = new Region();
        region38.setId("VN-38");
        region38.setName("BenTre");
        region38.setTitle("Bến Tre, Việt Nam");
        regionDao.saveObj(region38);

        Region region39 = new Region();
        region39.setId("VN-39");
        region39.setName("TraVinh");
        region39.setTitle("Trà Vinh, Việt Nam");
        regionDao.saveObj(region39);

        Region region40 = new Region();
        region40.setId("VN-40");
        region40.setName("SocTrang");
        region40.setTitle("Sóc Trăng, Việt Nam");
        regionDao.saveObj(region40);

        Region region41 = new Region();
        region41.setId("VN-41");
        region41.setName("BacKan");
        region41.setTitle("Bắc Kạn, Việt Nam");
        regionDao.saveObj(region41);

        Region region42 = new Region();
        region42.setId("VN-42");
        region42.setName("BacGiang");
        region42.setTitle("Bắc Giang, Việt Nam");
        regionDao.saveObj(region42);

        Region region43 = new Region();
        region43.setId("VN-43");
        region43.setName("BacLieu");
        region43.setTitle("Bạc Liêu, Việt Nam");
        regionDao.saveObj(region43);

        Region region44 = new Region();
        region44.setId("VN-44");
        region44.setName("BacNinh");
        region44.setTitle("Bắc Ninh, Việt Nam");
        regionDao.saveObj(region44);

        Region region45 = new Region();
        region45.setId("VN-45");
        region45.setName("BinhDuong");
        region45.setTitle("Bình Dương, Việt Nam");
        regionDao.saveObj(region45);

        Region region46 = new Region();
        region46.setId("VN-46");
        region46.setName("BinhPhuoc");
        region46.setTitle("Bình Phước, Việt Nam");
        regionDao.saveObj(region46);

        Region region47 = new Region();
        region47.setId("VN-47");
        region47.setName("CaMau");
        region47.setTitle("Cà Mau, Việt Nam");
        regionDao.saveObj(region47);

        Region region48 = new Region();
        region48.setId("VN-48");
        region48.setName("HaiDuong");
        region48.setTitle("Hải Dương, Việt Nam");
        regionDao.saveObj(region48);

        Region region49 = new Region();
        region49.setId("VN-49");
        region49.setName("HaNam");
        region49.setTitle("Hà Nam, Việt Nam");
        regionDao.saveObj(region49);



        Region region50 = new Region();
        region50.setId("VN-50");
        region50.setName("HungYen");
        region50.setTitle("Hưng Yên, Việt Nam");
        regionDao.saveObj(region50);

        Region region51 = new Region();
        region51.setId("VN-51");
        region51.setName("NamDinh");
        region51.setTitle("Nam Định, Việt Nam");
        regionDao.saveObj(region51);

        Region region52 = new Region();
        region52.setId("VN-52");
        region52.setName("PhuTho");
        region52.setTitle("Phú Thọ, Việt Nam");
        regionDao.saveObj(region52);

        Region region53 = new Region();
        region53.setId("VN-53");
        region53.setName("ThaiNguyen");
        region53.setTitle("Thái Nguyên, Việt Nam");
        regionDao.saveObj(region53);

        Region region54 = new Region();
        region54.setId("VN-54");
        region54.setName("VinhPhuc");
        region54.setTitle("Vĩnh Phúc, Việt Nam");
        regionDao.saveObj(region54);

        Region region55 = new Region();
        region55.setId("VN-55");
        region55.setName("DienBien");
        region55.setTitle("Điện Biên, Việt Nam");
        regionDao.saveObj(region55);

        Region region56 = new Region();
        region56.setId("VN-56");
        region56.setName("DakNong");
        region56.setTitle("Đắk Nông, Việt Nam");
        regionDao.saveObj(region56);

        Region region57 = new Region();
        region57.setId("VN-57");
        region57.setName("HauGiang");
        region57.setTitle("Hậu Giang, Việt Nam");
        regionDao.saveObj(region57);

        Region region58 = new Region();
        region58.setId("VN-58");
        region58.setName("CanTho");
        region58.setTitle("Cần Thơ, Việt Nam");
        regionDao.saveObj(region58);

        Region region59 = new Region();
        region59.setId("VN-59");
        region59.setName("HaNoi");
        region59.setTitle("Hà Nội, Việt Nam");
        regionDao.saveObj(region59);

        Region region60 = new Region();
        region60.setId("VN-60");
        region60.setName("HaiPhong");
        region60.setTitle("Hải Phòng, Việt Nam");
        regionDao.saveObj(region60);

        Region region61 = new Region();
        region61.setId("VN-61");
        region61.setName("HoChiMinh");
        region61.setTitle("Hồ Chí Minh, Việt Nam");
        regionDao.saveObj(region61);

        Region region62 = new Region();
        region62.setId("VN-62");
        region62.setName("DaNang");
        region62.setTitle("Đà Nẵng, Việt Nam");
        regionDao.saveObj(region62);

        Region region63 = new Region();
        region63.setId("VN-63");
        region63.setName("KhanhHoa");
        region63.setTitle("Khánh Hòa, Việt Nam");
        regionDao.saveObj(region63);

        Region region64 = new Region();
        region64.setId("VN-QDTS");
        region64.setName("QuanDaoTruongSa");
        region64.setTitle("Quần đảo Trường Sa, Khánh Hòa, Việt Nam");
        regionDao.saveObj(region64);

        Region region65 = new Region();
        region65.setId("VN-QDHS");
        region65.setName("QuanDaoHoangSa");
        region65.setTitle("Quần đảo Hoàng Sa, Đà Nẵng, Việt Nam");
        regionDao.saveObj(region65);


        // save places of GiaLai
        Region region = null;
        try {
            region = regionDao.findRegionById("VN-22");
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        Place place1 = new Place();
        place1.setName("HoTNung");
        place1.setTitle("Hồ T'Nưng");
        place1.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place1.setLatitude(-45.7679);
        place1.setLongitude(26.3213);
        place1.setPlaceStatus(PlaceStatus.AVAILABLE);
        place1.setRegion(region);
        placeDao.saveObj(place1);

        Place place2 = new Place();
        place2.setName("HoTroi");
        place2.setTitle("Hố Trời");
        place2.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place2.setLatitude(-42.0899);
        place2.setLongitude(15.425);
        place2.setPlaceStatus(PlaceStatus.AVAILABLE);
        place2.setRegion(region);
        placeDao.saveObj(place2);

        Place place3 = new Place();
        place3.setName("ChuDangYa");
        place3.setTitle("Núi lửa Chư Đăng Ya");
        place3.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place3.setLatitude(-39.5967);
        place3.setLongitude(24.2362);
        place3.setPlaceStatus(PlaceStatus.AVAILABLE);
        place3.setRegion(region);
        placeDao.saveObj(place3);

        Place place4 = new Place();
        place4.setName("ThacXungKhoeng");
        place4.setTitle("Thác Xung Khoeng");
        place4.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place4.setLatitude(-49.5403);
        place4.setLongitude(26.994);
        place4.setPlaceStatus(PlaceStatus.AVAILABLE);
        place4.setRegion(region);
        placeDao.saveObj(place4);

        return regionDao.findAll();
    }

}
