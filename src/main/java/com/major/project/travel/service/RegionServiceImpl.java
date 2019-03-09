package com.major.project.travel.service;

import com.major.project.travel.dao.*;
import com.major.project.travel.exception.DataNotFoundException;
import com.major.project.travel.exception.RestException;
import com.major.project.travel.model.*;
import com.major.project.travel.request.RegionRequest;
import com.major.project.travel.response.PlaceUserResponse;
import com.major.project.travel.response.RegionUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FeelingService feelingService;

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
    public Region findRegionById(String id) throws DataNotFoundException {
        return regionDao.findRegionById(id);
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

        // Save Role
        Role role = new Role();
        role.setRoleName("ADMIN");
        role.setRoleStatus(RoleStatus.ACTIVE);
        roleDao.saveObj(role);

        Role role1 = new Role();
        role1.setRoleName("USER");
        role1.setRoleStatus(RoleStatus.ACTIVE);
        roleDao.saveObj(role1);

        // Save User
        User user = new User();
        user.setUserName("Nguyễn Quang Huy");
        user.setUserStatus(UserStatus.ACTIVE);
        user.setAvatar("https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=2165468497037647&height=50&width=50&ext=1547266045&hash=AeSmrP_0PQsxbkme");
        user.setEmail("nguyenquanghuy605@gmail.com");
        user.setUserID(2165468497037647L);
        user.setPlaceVisited(9);
        user.setRegionVisited(3);
        user.setRole(role);
        userDao.saveObj(user);

        User user1 = new User();
        user1.setUserName("Lê Tấn Khang");
        user1.setUserStatus(UserStatus.ACTIVE);
        user1.setAvatar("https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=2165468497037647&height=50&width=50&ext=1547266045&hash=AeSmrP_0PQsxbkme");
        user1.setEmail("letankhang@gmail.com");
        user1.setUserID(2165468497037644L);
        user1.setRole(role1);
        userDao.saveObj(user1);

        User user2 = new User();
        user2.setUserName("Lâm Chí Hào");
        user2.setUserStatus(UserStatus.ACTIVE);
        user2.setAvatar("https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=2165468497037647&height=50&width=50&ext=1547266045&hash=AeSmrP_0PQsxbkme");
        user2.setEmail("haolam@gmail.com");
        user2.setUserID(2165468497037624L);
        user2.setRole(role1);
        userDao.saveObj(user2);

        User user3 = new User();
        user3.setUserName("QuangHuy");
        user3.setUserStatus(UserStatus.ACTIVE);
        user3.setEmail("quanghuy@gmail.com");
        user3.setPassword(passwordEncoder.encode("123"));
        user3.setUserID(2165438497037624L);
        user3.setRole(role);
        userDao.saveObj(user3);

        User user4 = new User();
        user4.setUserName("KhangLe");
        user4.setUserStatus(UserStatus.ACTIVE);
        user4.setEmail("khangle@gmail.com");
        user4.setPassword(passwordEncoder.encode("123"));
        user4.setUserID(2165438497035624L);
        user4.setRole(role);
        userDao.saveObj(user4);

        // Save UserRegion
        UserRegion userRegion = new UserRegion();
        userRegion.setUser(user);
        userRegion.setRegion(region21);
        userRegionDao.saveObj(userRegion);

        userRegion = new UserRegion();
        userRegion.setUser(user);
        userRegion.setRegion(region16);
        userRegionDao.saveObj(userRegion);

        userRegion = new UserRegion();
        userRegion.setUser(user);
        userRegion.setRegion(region14);
        userRegionDao.saveObj(userRegion);

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

        // Quang Ngai
        try {
            region = regionDao.findRegionById("VN-21");
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

        Place place5 = new Place();
        place5.setName("NuiThienAn");
        place5.setTitle("Núi Thiên Ấn");
        place5.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place5.setLatitude(-10.1859);
        place5.setLongitude(15.013);
        place5.setPlaceStatus(PlaceStatus.AVAILABLE);
        place5.setRegion(region);
        placeDao.saveObj(place5);

        Place place6 = new Place();
        place6.setName("ThacTrang");
        place6.setTitle("Thác Trắng");
        place6.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place6.setLatitude(-13.3284);
        place6.setLongitude(18.3737);
        place6.setPlaceStatus(PlaceStatus.AVAILABLE);
        place6.setRegion(region);
        placeDao.saveObj(place6);

        Place place7 = new Place();
        place7.setName("ChungTichSonMy");
        place7.setTitle("Chứng tích Sơn Mỹ");
        place7.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place7.setLatitude(-20.0966);
        place7.setLongitude(16.6649);
        place7.setPlaceStatus(PlaceStatus.AVAILABLE);
        place7.setRegion(region);
        placeDao.saveObj(place7);

        Place place8 = new Place();
        place8.setName("BienLeThuy");
        place8.setTitle("Biển Lệ Thủy");
        place8.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place8.setLatitude(-20.075);
        place8.setLongitude(11.8233);
        place8.setPlaceStatus(PlaceStatus.AVAILABLE);
        place8.setRegion(region);
        placeDao.saveObj(place8);

        Place place9 = new Place();
        place9.setName("BienMyKhe");
        place9.setTitle("Biển Mỹ Khê");
        place9.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place9.setLatitude(-14.8176);
        place9.setLongitude(12.4783);
        place9.setPlaceStatus(PlaceStatus.AVAILABLE);
        place9.setRegion(region);
        placeDao.saveObj(place9);

        //////////////////////////////////////
        // Nghe An
        try {
            region = regionDao.findRegionById("VN-14");
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        Place place10 = new Place();
        place10.setName("ThacXaoLa");
        place10.setTitle("Thác Xao La");
        place10.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place10.setLatitude(73.5804);
        place10.setLongitude(59.1337);
        place10.setPlaceStatus(PlaceStatus.AVAILABLE);
        place10.setRegion(region);
        placeDao.saveObj(place10);

        Place place11 = new Place();
        place11.setName("BienCuaLo");
        place11.setTitle("Biển Cửa Lò");
        place11.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place11.setLatitude(76.4642);
        place11.setLongitude(54.7581);
        place11.setPlaceStatus(PlaceStatus.AVAILABLE);
        place11.setRegion(region);
        placeDao.saveObj(place11);

        Place place12 = new Place();
        place12.setName("DoiCheThanhChuong");
        place12.setTitle("Đồi Chè Thanh Chương");
        place12.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place12.setLatitude(79.1192);
        place12.setLongitude(71.3915);
        place12.setPlaceStatus(PlaceStatus.AVAILABLE);
        place12.setRegion(region);
        placeDao.saveObj(place12);

        Place place13 = new Place();
        place13.setName("CanhDongHoaHuongDuong");
        place13.setTitle("Cánh Đồng Hoa Hướng Dương");
        place13.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place13.setLatitude(77.6002);
        place13.setLongitude(71.5837);
        place13.setPlaceStatus(PlaceStatus.AVAILABLE);
        place13.setRegion(region);
        placeDao.saveObj(place13);

        Place place14 = new Place();
        place14.setName("LangSenQueBac");
        place14.setTitle("Làng Sen quê Bác");
        place14.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place14.setLatitude(78.0961);
        place14.setLongitude(64.2285);
        place14.setPlaceStatus(PlaceStatus.AVAILABLE);
        place14.setRegion(region);
        placeDao.saveObj(place14);

        Place place15 = new Place();
        place15.setName("ThanhCoVinh");
        place15.setTitle("Thành cổ Vinh");
        place15.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place15.setLatitude(75.641);
        place15.setLongitude(63.2671);
        place15.setPlaceStatus(PlaceStatus.AVAILABLE);
        place15.setRegion(region);
        placeDao.saveObj(place15);

        Place place16 = new Place();
        place16.setName("VuonQuocGiaPuMat");
        place16.setTitle("Vườn quốc gia Pù Mát");
        place16.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place16.setLatitude(77.8754);
        place16.setLongitude(58.5559);
        place16.setPlaceStatus(PlaceStatus.AVAILABLE);
        place16.setRegion(region);
        placeDao.saveObj(place16);

        //////////////////////////////////////
        // Binh Thuan
        try {
            region = regionDao.findRegionById("VN-30");
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

        Place place17 = new Place();
        place17.setName("NuiTaCu");
        place17.setTitle("Núi Tà Cú");
        place17.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place17.setLatitude(-79.2322);
        place17.setLongitude(18.3989);
        place17.setPlaceStatus(PlaceStatus.AVAILABLE);
        place17.setRegion(region);
        placeDao.saveObj(place17);

        Place place18 = new Place();
        place18.setName("CongVienTuongCatForgottenLand");
        place18.setTitle("Công viên Tượng cát Forgotten Land");
        place18.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place18.setLatitude(-80.4274);
        place18.setLongitude(20.7588);
        place18.setPlaceStatus(PlaceStatus.AVAILABLE);
        place18.setRegion(region);
        placeDao.saveObj(place18);

        Place place19 = new Place();
        place19.setName("Mũi Yến");
        place19.setTitle("Mũi Yến");
        place19.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place19.setLatitude(-79.9974);
        place19.setLongitude(15.1909);
        place19.setPlaceStatus(PlaceStatus.AVAILABLE);
        place19.setRegion(region);
        placeDao.saveObj(place19);

        Place place20 = new Place();
        place20.setName("BaiDaOngDia");
        place20.setTitle("Bãi đá Ông Địa");
        place20.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place20.setLatitude(-81.3018);
        place20.setLongitude(22.8237);
        place20.setPlaceStatus(PlaceStatus.AVAILABLE);
        place20.setRegion(region);
        placeDao.saveObj(place20);

        Place place21 = new Place();
        place21.setName("TruongDucThanh");
        place21.setTitle("Trường Dục Thanh");
        place21.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place21.setLatitude(-80.6502);
        place21.setLongitude(26.511);
        place21.setPlaceStatus(PlaceStatus.AVAILABLE);
        place21.setRegion(region);
        placeDao.saveObj(place21);

        Place place22 = new Place();
        place22.setName("ThapChamPoshanư");
        place22.setTitle("Tháp Chàm Poshanư");
        place22.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place22.setLatitude(-79.7592);
        place22.setLongitude(20.5006);
        place22.setPlaceStatus(PlaceStatus.AVAILABLE);
        place22.setRegion(region);
        placeDao.saveObj(place22);

        Place place23 = new Place();
        place23.setName("DoiCatHoaThang");
        place23.setTitle("Đồi cát Hoà Thắng");
        place23.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place23.setLatitude(-81.8222);
        place23.setLongitude(27.2765);
        place23.setPlaceStatus(PlaceStatus.AVAILABLE);
        place23.setRegion(region);
        placeDao.saveObj(place23);

        // Quang Binh
        try {
            region = regionDao.findRegionById("VN-16");
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

        Place place24 = new Place();
        place24.setName("HangEn");
        place24.setTitle("Hang Én");
        place24.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place24.setLatitude(60.1533);
        place24.setLongitude(45.7699);
        place24.setPlaceStatus(PlaceStatus.AVAILABLE);
        place24.setRegion(region);
        placeDao.saveObj(place24);

        Place place25 = new Place();
        place25.setName("DongPhongNha");
        place25.setTitle("Động Phong Nha");
        place25.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place25.setLatitude(57.239);
        place25.setLongitude(44.2047);
        place25.setPlaceStatus(PlaceStatus.AVAILABLE);
        place25.setRegion(region);
        placeDao.saveObj(place25);

        Place place26 = new Place();
        place26.setName("DongTienSon");
        place26.setTitle("Động Tiên Sơn");
        place26.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place26.setLatitude(51.7626);
        place26.setLongitude(41.4178);
        place26.setPlaceStatus(PlaceStatus.AVAILABLE);
        place26.setRegion(region);
        placeDao.saveObj(place26);

        Place place27 = new Place();
        place27.setName("DongThienDuong");
        place27.setTitle("Động Thiên Đường");
        place27.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place27.setLatitude(55.0508);
        place27.setLongitude(42.5249);
        place27.setPlaceStatus(PlaceStatus.AVAILABLE);
        place27.setRegion(region);
        placeDao.saveObj(place27);

        Place place28 = new Place();
        place28.setName("HangSonDoong");
        place28.setTitle("Hang Sơn Đoòng");
        place28.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place28.setLatitude(55.0508);
        place28.setLongitude(42.5249);
        place28.setPlaceStatus(PlaceStatus.AVAILABLE);
        place28.setRegion(region);
        placeDao.saveObj(place28);

        Place place29 = new Place();
        place29.setName("SuoiNuocMooc");
        place29.setTitle("Suối Nước Moọc");
        place29.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place29.setLatitude(55.8991);
        place29.setLongitude(46.1517);
        place29.setPlaceStatus(PlaceStatus.AVAILABLE);
        place29.setRegion(region);
        placeDao.saveObj(place29);

        Place place30 = new Place();
        place30.setName("BaiDaNhay");
        place30.setTitle("Bãi Đá Nhảy");
        place30.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place30.setLatitude(49.0202);
        place30.setLongitude(39.089);
        place30.setPlaceStatus(PlaceStatus.AVAILABLE);
        place30.setRegion(region);
        placeDao.saveObj(place30);

        Place place31 = new Place();
        place31.setName("BauTro");
        place31.setTitle("Bàu Tró");
        place31.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place31.setLatitude(47.3995);
        place31.setLongitude(41.3796);
        place31.setPlaceStatus(PlaceStatus.AVAILABLE);
        place31.setRegion(region);
        placeDao.saveObj(place31);

        Place place32 = new Place();
        place32.setName("NuiThanDinh");
        place32.setTitle("Núi Thần Đinh");
        place32.setSvgPath("M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z");
        place32.setLatitude(59.8728);
        place32.setLongitude(50.6565);
        place32.setPlaceStatus(PlaceStatus.AVAILABLE);
        place32.setRegion(region);
        placeDao.saveObj(place32);

        // Save UserPlace
        PlaceUser placeUser = new PlaceUser();
        placeUser.setUser(user);
        placeUser.setPlace(place5);
        placeUserDao.saveObj(placeUser);

        PlaceUser placeUser1 = new PlaceUser();
        placeUser1.setUser(user);
        placeUser1.setPlace(place6);
        placeUserDao.saveObj(placeUser1);

        PlaceUser placeUser2 = new PlaceUser();
        placeUser2.setUser(user);
        placeUser2.setPlace(place7);
        placeUserDao.saveObj(placeUser2);

        PlaceUser placeUser3 = new PlaceUser();
        placeUser3.setUser(user);
        placeUser3.setPlace(place9);
        placeUserDao.saveObj(placeUser3);

        PlaceUser placeUser4 = new PlaceUser();
        placeUser4.setUser(user);
        placeUser4.setPlace(place10);
        placeUserDao.saveObj(placeUser4);

        PlaceUser placeUser5 = new PlaceUser();
        placeUser5.setUser(user);
        placeUser5.setPlace(place12);
        placeUserDao.saveObj(placeUser5);

        PlaceUser placeUser6 = new PlaceUser();
        placeUser6.setUser(user);
        placeUser6.setPlace(place13);
        placeUserDao.saveObj(placeUser6);

        PlaceUser placeUser7 = new PlaceUser();
        placeUser7.setUser(user);
        placeUser7.setPlace(place18);
        placeUserDao.saveObj(placeUser7);

        // Save Feeling
        Feeling feeling = new Feeling();
        feeling.setTopic("Ngày Quốc Khánh");
        feeling.setContent("Một ngày thật tuyệt vời với một địa danh tuyệt đẹp như trong mơ");
        feeling.setFeelingStatus(FeelingStatus.APPROVED);
        feeling.setPlaceUser(placeUser);
        feeling.setUserRegion(userRegion);
        try {
            feelingService.save(feeling);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

        Feeling feeling1 = new Feeling();
        feeling1.setTopic("Ngày Tết Tây");
        feeling1.setContent("Đi tham quan cùng bạn bè và chụp những bức hình tuyệt đẹp nào!");
        feeling1.setFeelingStatus(FeelingStatus.APPROVED);
        feeling1.setPlaceUser(placeUser2);
        feeling1.setUserRegion(userRegion);
        try {
            feelingService.save(feeling1);
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }

        return regionDao.findAll();

    }
}
