package org.example.trackerproducer.config;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ClickStreamProcessor {
    @Bean
    public KStream<String, String> processClicks(StreamsBuilder builder) {
        KStream<String, String> clicks = builder.stream("clicks");
        KTable<String, Long> counts = clicks
                .groupByKey()
                .count(Materialized.as("click-count-store"));

        counts.toStream()
                .mapValues(String::valueOf)
                .to("click-counts", Produced.with(Serdes.String(), Serdes.String()));
        return clicks;
    }
}
