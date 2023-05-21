package hcmute.edu.vn.finalproject20110451.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "giaodich")
public class GiaoDichEntity extends BaseEntity{
    @Id
    private String id;

    private String orderId;

    private float soTienThanhToan;

    //0 là thất bại, 1 là thành công, 2 là đang xử lý
    private int trangThaiGiaoDich;
}
