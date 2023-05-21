package hcmute.edu.vn.finalproject20110451.entity;

import hcmute.edu.vn.finalproject20110451.entity.embedded.ThongTinKhach;
import hcmute.edu.vn.finalproject20110451.entity.embedded.ThongTinOrder;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dathang")
public class DatHangEntity extends BaseEntity{
    @Id
    private String id;

    private ThongTinKhach thongTinKhach;

    private ThongTinOrder thongTinOrder;

    private float tongTien;
}
