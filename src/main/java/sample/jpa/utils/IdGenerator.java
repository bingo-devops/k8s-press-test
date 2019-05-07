package sample.jpa.utils;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.Instant;
import java.util.Properties;

public class IdGenerator implements Configurable, IdentifierGenerator {

    private String prefix;

    private static String getSerial() {
        Instant now = Instant.now(new NanoClock());
        long time = now.getEpochSecond() * 1000000000L + now.getNano();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(0, time);

        String serial = new BigInteger(buffer.array()).toString(32);
        return String.format("%13s", serial).replace(' ', '0');
    }

    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
        this.prefix = properties.getProperty("prefix");
        if (StringUtils.isEmpty(this.prefix)) {
            this.prefix = properties.getProperty("jpa_entity_name").substring(0, 1).toLowerCase();
        }
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return this.prefix + "-" + getSerial();
    }
}
