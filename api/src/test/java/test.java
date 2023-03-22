import com.project.utils.StringUtil;
import com.project.api.service.APIService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class test {
    @Mock
    private APIService apiService;

    @Test
    public void test(){
        System.out.println("test1");
        System.out.println(StringUtil.dosiChange("경기도"));
    }
}
